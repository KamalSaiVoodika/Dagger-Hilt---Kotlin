package com.pos.ui.sales_open

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pos.BR
import com.pos.R
import com.pos.base.BaseFragment
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.FragmentSalesOpenBinding
import com.pos.ui.sales_order_list.SalesOrderListNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SalesOpenFragment(var listNavigator: SalesOrderListNavigator) :
    BaseFragment<FragmentSalesOpenBinding, SalesOpenViewModel>(),
    SalesOpenNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: SalesOpenViewModel? = null

    lateinit var binding: FragmentSalesOpenBinding

    lateinit var adapter: SalesOpenAdapter

    override fun getViewModel(): SalesOpenViewModel {
        viewModel = ViewModelProvider(this).get(SalesOpenViewModel::class.java)
        return viewModel as SalesOpenViewModel
    }

    override fun getBindingVariable(): Int = BR._all

    override fun getLayoutId(): Int = R.layout.fragment_sales_open

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.myViewModel = viewModel

        setAdapter()

    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(getBaseActivity())
        adapter = SalesOpenAdapter(getBaseActivity())
        binding.rvTsp.layoutManager = layoutManager
        binding.rvTsp.isNestedScrollingEnabled = false
        binding.rvTsp.adapter = adapter
        adapter.setNavigator(this)
    }

    override fun onItemClick() {
        listNavigator.onSalesDetailsClick()
    }

}