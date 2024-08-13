package com.pos.data.local

import android.content.Context
import android.content.SharedPreferences
import com.pos.di.module.PreferenceInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(
    @ApplicationContext context: Context,
    @PreferenceInfo prefFileName: String?
) : PreferencesHelper {

    private val mPrefs: SharedPreferences

    override fun clearData() {
        mPrefs.edit().clear().apply()
    }

    override fun getUserId(): String {
        return mPrefs.getString(PREF_USER_ID, "0")!!
    }

    override fun setUserId(userId: String?) {
        mPrefs.edit().putString(PREF_USER_ID, userId).apply()
    }

    override fun getToken(): String {
        return mPrefs.getString(PREF_TOKEN, "")!!
    }

    override fun setToken(token: String?) {
        mPrefs.edit().putString(PREF_TOKEN, token).apply()
    }

    companion object {
        private const val PREF_USER_ID = "PREF_USER_ID"
        private const val PREF_TOKEN = "PREF_TOKEN"
    }

    init {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }
}