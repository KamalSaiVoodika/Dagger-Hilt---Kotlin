package com.pos.data.api

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.pos.data.local.PreferencesHelper
import com.pos.utils.Utils.logPrint
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import java.io.IOException
import kotlin.jvm.Throws


class LoggingInterceptor(
    val context: Context,
    apiHeader: ApiHeader,
    preferencesHelper: PreferencesHelper
) : Interceptor {
    var mPreferencesHelper: PreferencesHelper
    var apiHeader: ApiHeader

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        Log.e("request", original.toString())
        val originalHttpUrl = original.url

        val url: HttpUrl
        val requestBuilder: Request.Builder

        url = originalHttpUrl.newBuilder()
            /*.addQueryParameter("Token", mPreferencesHelper.getToken())*/.build()
        requestBuilder = original.newBuilder().url(url).headers(apiHeader.headers!!)

        val request = requestBuilder.build()
        val t1 = System.nanoTime()
        var body: String? = null
        if (request.body != null) {
            val buffer = Buffer()
            request.body!!.writeTo(buffer)
            body = buffer.readUtf8()
        }
        logPrint(
            String.format(
                "Sending request %s on %s%n%s\nBody : %s",
                request.url,
                chain.connection(),
                request.headers,
                body
            )
        )
        val response = chain.proceed(request)
        val t2 = System.nanoTime()
        logPrint(
            String.format(
                "Received response code %s for %s in %.1fms%n%s",
                response.code,
                response.request.url,
                (t2 - t1) / 1e6,
                response.headers
            )
        )
        val responseString = String(response.body!!.bytes())
        Log.e(":::::::RESPONSE:::::::", responseString)
        logPrint("::::::::::RESPONSE:::::::::$responseString")
        if (response.code == 200) { // 440 means token expired
            Handler(Looper.getMainLooper()).post {

                /* try {
                     val jsonObject = JSONObject(responseString)
                     if (jsonObject.getInt("code") == 1001) {
                         val i = Intent(context, ErrorActivity::class.java)
                         i.putExtra(
                             config.ARG_MESSAGE,
                             JSONObject(responseString).getString("message")
                         )
                         i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                         context.startActivity(i)
                     }
                 } catch (e: JSONException) {
                     e.printStackTrace()
                 }*/
            }
        } else if (response.code != 200) {
            Handler(Looper.getMainLooper()).post {
                /*try {
                    val i = Intent(context, ErrorActivity::class.java)
                    i.putExtra(config.ARG_MESSAGE, JSONObject(responseString).getString("message"))
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(i)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }*/
            }
        }
        return response.newBuilder()
            .body(responseString.toResponseBody(response.body!!.contentType())).build()
    }

    fun getMessageFromCode(code: Int): String {
        return when (code) {
            200 -> ""
            401 -> "Unauthorized Error"
            403 -> ""
            404 -> "Page Not Found"
            405 -> "Method Not Allowed"
            423 -> ""
            440 -> ""
            500 -> "Internal Server Error"
            504 -> "Server Time out"
            else -> "Unknown Error"
        }
    }

    init {
        mPreferencesHelper = preferencesHelper
        this.apiHeader = apiHeader
    }
}