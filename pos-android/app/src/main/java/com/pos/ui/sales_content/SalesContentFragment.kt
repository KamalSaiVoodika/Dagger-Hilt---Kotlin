package com.pos.ui.sales_content

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.pos.BR
import com.pos.R
import com.pos.base.BaseFragment
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.FragmentSalesContentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SalesContentFragment : BaseFragment<FragmentSalesContentBinding, SalesContentViewModel>(),
    SalesContentNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: SalesContentViewModel? = null

    lateinit var binding: FragmentSalesContentBinding

    lateinit var adapter: SalesContentAdapter

    override fun getViewModel(): SalesContentViewModel {
        viewModel = ViewModelProvider(this).get(SalesContentViewModel::class.java)
        return viewModel as SalesContentViewModel
    }

    override fun getBindingVariable(): Int = BR._all

    override fun getLayoutId(): Int = R.layout.fragment_sales_content

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.myViewModel = viewModel

        setAdapter()

    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(getBaseActivity())
        adapter = SalesContentAdapter(getBaseActivity()!!)
        binding.rvTsp.layoutManager = layoutManager
        binding.rvTsp.isNestedScrollingEnabled = false
        binding.rvTsp.adapter = adapter
    }

}