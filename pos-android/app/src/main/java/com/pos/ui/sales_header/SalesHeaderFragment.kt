package com.pos.ui.sales_header

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.pos.BR
import com.pos.R
import com.pos.base.BaseFragment
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.FragmentSalesHeaderBinding
import com.pos.ui.sales_order_details.SalesOrderDetailsNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SalesHeaderFragment(var detailNavigator: SalesOrderDetailsNavigator) :
    BaseFragment<FragmentSalesHeaderBinding, SalesHeaderViewModel>(),
    SalesHeaderNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: SalesHeaderViewModel? = null

    lateinit var binding: FragmentSalesHeaderBinding


    override fun getViewModel(): SalesHeaderViewModel {
        viewModel = ViewModelProvider(this).get(SalesHeaderViewModel::class.java)
        return viewModel as SalesHeaderViewModel
    }

    override fun getBindingVariable(): Int = BR._all

    override fun getLayoutId(): Int = R.layout.fragment_sales_header

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.myViewModel = viewModel

    }

    override fun onAllItemClick() {
        detailNavigator.onAllItemsClick()
    }

}