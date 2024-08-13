package com.pos.ui.sales_approval

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
import com.pos.databinding.FragmentSalesApproveBinding
import com.pos.databinding.FragmentSalesContentBinding
import com.pos.ui.sales_all.SalesAllAdapter
import com.pos.ui.sales_all.SalesAllNavigator
import com.pos.ui.sales_order_list.SalesOrderListNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SalesApprovalFragment (var listNavigator: SalesOrderListNavigator) : BaseFragment<FragmentSalesApproveBinding, SalesApprovalViewModel>(),
    SalesApprovalNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: SalesApprovalViewModel? = null

    lateinit var binding: FragmentSalesApproveBinding

    lateinit var adapter: SalesApprovalAdapter

    override fun getViewModel(): SalesApprovalViewModel {
        viewModel = ViewModelProvider(this).get(SalesApprovalViewModel::class.java)
        return viewModel as SalesApprovalViewModel
    }

    override fun getBindingVariable(): Int = BR._all

    override fun getLayoutId(): Int = R.layout.fragment_sales_approve

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.myViewModel = viewModel

        setAdapter()

    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(getBaseActivity())
        adapter = SalesApprovalAdapter(getBaseActivity())
        binding.rvTsp.layoutManager = layoutManager
        binding.rvTsp.isNestedScrollingEnabled = false
        binding.rvTsp.adapter = adapter
        adapter.setNavigator(this)
    }

    override fun onItemClick() {
        listNavigator.onSalesDetailsClick()
    }

}