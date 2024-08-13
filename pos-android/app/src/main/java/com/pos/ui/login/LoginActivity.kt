package com.pos.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pos.BR
import com.pos.R
import com.pos.base.BaseActivity
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.ActivityLoginBinding
import com.pos.ui.home.HomeActivity
import com.pos.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: LoginViewModel? = null

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.loginViewModel = viewModel

        setUpObserver()

    }

    override fun getViewModel(): LoginViewModel {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        return viewModel as LoginViewModel
    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun getBindingVariable(): Int = BR._all


    private fun setUpObserver() {
        viewModel?.loginData?.observe(this@LoginActivity, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgress()
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    hideProgress()
                    if (it.message!!.isNotEmpty()) {
                        showMessageAlert(it.message)
                    }
                }
            }
        })
    }

    override fun onSubmitClick() {
        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
       // startActivity(Intent(this@LoginActivity, SalesOrderListActivity::class.java))
    }
}