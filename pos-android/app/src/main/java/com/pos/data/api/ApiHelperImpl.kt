package com.pos.data.api

import com.pos.data.local.AppPreferencesHelper
import com.pos.data.model.BaseResponse
import com.pos.data.model.LoginBody
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService,
    private val mPreferencesHelper: AppPreferencesHelper
) : ApiHelper {

    override suspend fun login(
        email: String,
        password: String
    ): Response<BaseResponse> {

        val credential =
            LoginBody(email, password)
        val response = apiService.login(credential)

        return response
    }

    override suspend fun preferenceList(
    ): Response<BaseResponse> {
        return apiService.preferenceList()
    }

}