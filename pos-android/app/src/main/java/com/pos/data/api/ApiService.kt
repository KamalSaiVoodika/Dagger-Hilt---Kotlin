package com.pos.data.api

import com.pos.data.model.BaseResponse
import com.pos.data.model.LoginBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST(EndPoint.LOGIN)
    suspend fun login(
        @Body userCredential: LoginBody
    ): Response<BaseResponse>

    @GET(EndPoint.PREFERENCE_LIST)
    suspend fun preferenceList(
    ): Response<BaseResponse>

}