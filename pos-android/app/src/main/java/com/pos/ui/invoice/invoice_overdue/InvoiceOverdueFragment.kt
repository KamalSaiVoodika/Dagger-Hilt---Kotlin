package com.pos.ui.invoice.invoice_overdue

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pos.BR
import com.pos.R
import com.pos.base.BaseFragment
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.FragmentInvoiceOverdueBinding
import com.pos.databinding.FragmentSalesOpenBinding
import com.pos.ui.invoice.invoice_tabs.InvoiceTabsNavigator
import com.pos.ui.sales_order_list.SalesOrderListNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class InvoiceOverdueFragment(var listNavigator: InvoiceTabsNavigator) :
    BaseFragment<FragmentInvoiceOverdueBinding, InvoiceOverdueViewModel>(),
    InvoiceOverdueNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: InvoiceOverdueViewModel? = null

    lateinit var binding: FragmentInvoiceOverdueBinding

    lateinit var adapter: InvoiceOverdueAdapter

    override fun getViewModel(): InvoiceOverdueViewModel {
        viewModel = ViewModelProvider(this).get(InvoiceOverdueViewModel::class.java)
        return viewModel as InvoiceOverdueViewModel
    }

    override fun getBindingVariable(): Int = BR._all

    override fun getLayoutId(): Int = R.layout.fragment_invoice_overdue

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.myViewModel = viewModel

        setAdapter()

    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(getBaseActivity())
        adapter = InvoiceOverdueAdapter(getBaseActivity())
        binding.rvTsp.layoutManager = layoutManager
        binding.rvTsp.isNestedScrollingEnabled = false
        binding.rvTsp.adapter = adapter
        adapter.setNavigator(this)
    }

    override fun onItemClick() {
        listNavigator.onSalesDetailsClick()
    }

}