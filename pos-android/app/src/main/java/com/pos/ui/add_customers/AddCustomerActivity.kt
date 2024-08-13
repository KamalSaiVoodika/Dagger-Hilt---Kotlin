package com.pos.ui.add_customer

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.pos.BR
import com.pos.R
import com.pos.base.BaseActivity
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.ActivityAddCustomerBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AddCustomerActivity : BaseActivity<ActivityAddCustomerBinding, AddCustomerViewModel>(),
    AddCustomerNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: AddCustomerViewModel? = null

    lateinit var binding: ActivityAddCustomerBinding

    override fun getViewModel(): AddCustomerViewModel {
        viewModel = ViewModelProvider(this).get(AddCustomerViewModel::class.java)
        return viewModel as AddCustomerViewModel
    }

    override fun getLayoutId(): Int = R.layout.activity_add_customer

    override fun getBindingVariable(): Int = BR._all

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.addCustomerViewModel = viewModel

    }

    override fun onBackClick() {
        onBackPressed()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


}