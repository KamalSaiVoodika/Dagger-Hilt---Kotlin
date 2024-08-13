package com.pos.data.api

import com.pos.data.local.PreferencesHelper
import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiHeader @Inject constructor(var preferencesHelper: PreferencesHelper) {
    var stringStringMap: Map<String, String>? = null

    var headers: Headers? = null
        get() {
            stringStringMap = if (preferencesHelper.getToken()!!.isNotEmpty()) {
                mapOf(
                        //"user-id" to preferencesHelper.getUserId().toString(),
                        "Authorization" to "Bearer ${preferencesHelper.getToken().toString()}"
                )
            } else {
                mapOf()
            }
            return stringStringMap!!.toHeaders()
        }
        private set
}