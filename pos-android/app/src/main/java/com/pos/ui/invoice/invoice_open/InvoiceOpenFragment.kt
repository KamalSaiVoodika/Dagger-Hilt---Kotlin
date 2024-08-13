package com.pos.ui.invoice.invoice_open

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pos.BR
import com.pos.R
import com.pos.base.BaseFragment
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.FragmentInvoiceAllBinding
import com.pos.databinding.FragmentInvoiceOpenBinding
import com.pos.ui.invoice.invoice_tabs.InvoiceTabsNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class InvoiceOpenFragment(var listNavigator: InvoiceTabsNavigator) : BaseFragment<FragmentInvoiceOpenBinding, InvoiceOpenViewModel>(), InvoiceOpenNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: InvoiceOpenViewModel? = null

    lateinit var binding: FragmentInvoiceOpenBinding

    lateinit var adapter: InvoiceOpenAdapter

    override fun getViewModel(): InvoiceOpenViewModel {
        viewModel = ViewModelProvider(this).get(InvoiceOpenViewModel::class.java)
        return viewModel as InvoiceOpenViewModel
    }

    override fun getBindingVariable(): Int = BR._all

    override fun getLayoutId(): Int = R.layout.fragment_invoice_open

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.myViewModel = viewModel

        setAdapter()

    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(getBaseActivity())
        adapter = InvoiceOpenAdapter(getBaseActivity())
        binding.rvInvoiceOpen.layoutManager = layoutManager
        binding.rvInvoiceOpen.isNestedScrollingEnabled = false
        binding.rvInvoiceOpen.adapter = adapter
        adapter.setNavigator(this)
    }

    override fun onItemClick() {
        listNavigator.onSalesDetailsClick()
    }

}