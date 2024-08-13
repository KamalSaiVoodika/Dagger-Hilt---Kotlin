package com.pos.ui.sales_order_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.pos.BR
import com.pos.R
import com.pos.base.BaseActivity
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.ActivitySalesOrderListBinding
import com.pos.ui.sales_order_details.SalesOrderDetailsActivity
import com.pos.ui.sales_order_form.SalesOrderFormActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SalesOrderListActivity :
    BaseActivity<ActivitySalesOrderListBinding, SalesOrderListViewModel>(),
    SalesOrderListNavigator {

    val salesArray = arrayOf(
        "Open",
        "All",
        "Approval Status",
    )

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: SalesOrderListViewModel? = null

    lateinit var binding: ActivitySalesOrderListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        binding.salesOrderListModel = viewModel
        viewModel?.setNavigator(this)
        setAdapter()
        setTabLayout()
        initListeners()
    }

    private fun initListeners() {
        binding.rlFab.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this,
                    SalesOrderFormActivity::class.java
                )
            )
        })
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


    override fun getViewModel(): SalesOrderListViewModel {
        viewModel = ViewModelProvider(this).get(SalesOrderListViewModel::class.java)
        return viewModel as SalesOrderListViewModel
    }

    override fun getLayoutId(): Int = R.layout.activity_sales_order_list

    override fun getBindingVariable(): Int = BR._all

    override fun onBackClick() {
        onBackPressed()
    }

    override fun onSalesDetailsClick() {
        startActivity(Intent(this, SalesOrderDetailsActivity::class.java))
    }

    override fun onAddOrderClick() {
        startActivity(Intent(this, SalesOrderFormActivity::class.java))
    }

}