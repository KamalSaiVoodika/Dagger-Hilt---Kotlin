package com.pos.ui.sales_order_details

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.pos.BR
import com.pos.R
import com.pos.base.BaseActivity
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.ActivitySalesOrderDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SalesOrderDetailsActivity :
    BaseActivity<ActivitySalesOrderDetailsBinding, SalesOrderDetailsViewModel>(),
    SalesOrderDetailsNavigator {

    val salesArray = arrayOf(
        "Header",
        "Content",
        "Logistics",
        "Account"
    )

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: SalesOrderDetailsViewModel? = null

    lateinit var binding: ActivitySalesOrderDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        viewModel?.setNavigator(this)

        setAdapter()
        setTabLayout()
    }

    private fun setAdapter() {
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, this)
        binding.viewPager.adapter = adapter
    }

    private fun setTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = salesArray[position]
        }.attach()
    }


    override fun getViewModel(): SalesOrderDetailsViewModel {
        viewModel = ViewModelProvider(this).get(SalesOrderDetailsViewModel::class.java)
        return viewModel as SalesOrderDetailsViewModel
    }

    override fun getLayoutId(): Int = R.layout.activity_sales_order_details

    override fun getBindingVariable(): Int = BR._all

    override fun onBackClick() {
        onBackPressed()
    }

    override fun onAllItemsClick() {
        binding.viewPager.currentItem = 1
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


}