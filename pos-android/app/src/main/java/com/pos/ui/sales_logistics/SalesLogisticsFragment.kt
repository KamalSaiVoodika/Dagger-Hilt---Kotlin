package com.pos.ui.sales_logistics

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.pos.BR
import com.pos.R
import com.pos.base.BaseFragment
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.FragmentSalesLogisticsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SalesLogisticsFragment :
    BaseFragment<FragmentSalesLogisticsBinding, SalesLogisticsViewModel>(),
    SalesLogisticsNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: SalesLogisticsViewModel? = null

    lateinit var binding: FragmentSalesLogisticsBinding


    override fun getViewModel(): SalesLogisticsViewModel {
        viewModel = ViewModelProvider(this).get(SalesLogisticsViewModel::class.java)
        return viewModel as SalesLogisticsViewModel
    }

    override fun getBindingVariable(): Int = BR._all

    override fun getLayoutId(): Int = R.layout.fragment_sales_logistics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.myViewModel = viewModel

    }

}