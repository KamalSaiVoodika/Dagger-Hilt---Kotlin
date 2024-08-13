package com.pos.ui.sales_orders

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.pos.BR
import com.pos.R
import com.pos.base.BaseActivity
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.ActivitySalesOrdersBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SalesOrdersActivity : BaseActivity<ActivitySalesOrdersBinding, SalesOrdersViewModel>(),
    SalesOrdersNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: SalesOrdersViewModel? = null

    lateinit var binding: ActivitySalesOrdersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
    }

    override fun getViewModel(): SalesOrdersViewModel {
        viewModel = ViewModelProvider(this).get(SalesOrdersViewModel::class.java)
        return viewModel as SalesOrdersViewModel
    }

    override fun getLayoutId(): Int = R.layout.activity_sales_orders

    override fun getBindingVariable(): Int = BR._all

    override fun onBackClick() {
        onBackPressed()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}