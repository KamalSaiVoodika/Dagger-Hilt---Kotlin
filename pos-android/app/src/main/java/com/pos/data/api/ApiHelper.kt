package com.pos.data.api


import com.pos.data.model.BaseResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun login(
        email: String,
        password: String
    ): Response<BaseResponse>

    suspend fun preferenceList(
    ): Response<BaseResponse>

}