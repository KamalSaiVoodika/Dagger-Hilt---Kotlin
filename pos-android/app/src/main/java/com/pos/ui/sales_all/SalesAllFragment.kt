package com.pos.ui.sales_all

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.pos.BR
import com.pos.R
import com.pos.base.BaseFragment
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.FragmentSalesAllBinding
import com.pos.databinding.FragmentSalesContentBinding
import com.pos.ui.sales_order_list.SalesOrderListNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SalesAllFragment (var listNavigator: SalesOrderListNavigator) : BaseFragment<FragmentSalesAllBinding, SalesAllViewModel>(),
    SalesAllNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: SalesAllViewModel? = null

    lateinit var binding: FragmentSalesAllBinding

    lateinit var adapter: SalesAllAdapter

    override fun getViewModel(): SalesAllViewModel {
        viewModel = ViewModelProvider(this).get(SalesAllViewModel::class.java)
        return viewModel as SalesAllViewModel
    }

    override fun getBindingVariable(): Int = BR._all

    override fun getLayoutId(): Int = R.layout.fragment_sales_all

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.myViewModel = viewModel

        setAdapter()

    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(getBaseActivity())
        adapter = SalesAllAdapter(getBaseActivity())
        binding.rvTsp.layoutManager = layoutManager
        binding.rvTsp.isNestedScrollingEnabled = false
        binding.rvTsp.adapter = adapter
        adapter.setNavigator(this)
    }

    override fun onItemClick() {
        listNavigator.onSalesDetailsClick()
    }

}