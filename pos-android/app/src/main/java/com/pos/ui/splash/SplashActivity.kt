package com.pos.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.pos.R
import com.pos.data.local.AppPreferencesHelper
import com.pos.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var helper: AppPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            checkConditions()
        }, 3000)

    }

    private fun checkConditions() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finishAffinity()
    }

    override fun onBackPressed() {

    }

}