package com.pos.data.local

interface PreferencesHelper {

    fun getUserId(): String?

    fun setUserId(userId: String?)

    fun getToken(): String?

    fun setToken(token: String?)

    fun clearData()

}