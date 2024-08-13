package com.pos.ui.customers_list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pos.BR
import com.pos.R
import com.pos.base.BaseFragment
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.FragmentCustomersListBinding
import com.pos.databinding.FragmentSalesLogisticsBinding
import com.pos.ui.add_customer.AddCustomerActivity
import com.pos.ui.sales_all.SalesAllAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CustomersListFragment : BaseFragment<FragmentCustomersListBinding, CustomersListViewModel>(),
    CustomersListNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: CustomersListViewModel? = null

    lateinit var binding: FragmentCustomersListBinding

    lateinit var adapter: CustomersListAdapter


    override fun getViewModel(): CustomersListViewModel {
        viewModel = ViewModelProvider(this).get(CustomersListViewModel::class.java)
        return viewModel as CustomersListViewModel
    }

    override fun getBindingVariable(): Int = BR._all

    override fun getLayoutId(): Int = R.layout.fragment_customers_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.customersListViewModel = viewModel

        setAdapter()
    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(getBaseActivity())
        adapter = CustomersListAdapter(getBaseActivity())
        binding.rvCustomers.layoutManager = layoutManager
        binding.rvCustomers.isNestedScrollingEnabled = false
        binding.rvCustomers.adapter = adapter
        adapter.setNavigator(this)
    }

    override fun onAddCustomerClick() {
        startActivity(Intent(getBaseActivity(), AddCustomerActivity::class.java))
    }

}