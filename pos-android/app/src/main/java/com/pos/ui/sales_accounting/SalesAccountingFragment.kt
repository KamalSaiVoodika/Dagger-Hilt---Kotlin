package com.pos.ui.sales_accounting

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.pos.BR
import com.pos.R
import com.pos.base.BaseFragment
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.FragmentSalesAccountingBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SalesAccountingFragment :
    BaseFragment<FragmentSalesAccountingBinding, SalesAccountingViewModel>(),
    SalesAccountingNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: SalesAccountingViewModel? = null

    lateinit var binding: FragmentSalesAccountingBinding


    override fun getViewModel(): SalesAccountingViewModel {
        viewModel = ViewModelProvider(this).get(SalesAccountingViewModel::class.java)
        return viewModel as SalesAccountingViewModel
    }

    override fun getBindingVariable(): Int = BR._all

    override fun getLayoutId(): Int = R.layout.fragment_sales_accounting

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.myViewModel = viewModel

    }

    override fun onBottomSheetClick() {
        initializeBottomSheet()
    }

    private fun initializeBottomSheet() {
        val dialog = BottomSheetDialog(getBaseActivity())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_sales_accounting, null)
        dialog.setContentView(view)
        dialog.show()
    }

}