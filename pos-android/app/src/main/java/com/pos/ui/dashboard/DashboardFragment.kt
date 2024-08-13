package com.pos.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hadiidbouk.charts.BarData
import com.pos.BR
import com.pos.R
import com.pos.base.BaseFragment
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.FragmentDashboardBinding
import com.pos.ui.contacts.ContactsActivity
import com.pos.ui.customers_list.CustomersListFragment
import com.pos.ui.invoice.invoice_tabs.InvoiceTabsActivity
import com.pos.ui.product_items.ProductItemsFragment
import com.pos.ui.sales_order_form.SalesOrderFormActivity
import com.pos.ui.sales_order_list.SalesOrderListActivity
import com.pos.ui.selected_items.SelectedItemsFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>(),
    DashboardNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: DashboardViewModel? = null

    lateinit var binding: FragmentDashboardBinding

    lateinit var adapter: DashboardTSPAdapter

    override fun getViewModel(): DashboardViewModel {
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        return viewModel as DashboardViewModel
    }

    override fun getBindingVariable(): Int = BR._all

    override fun getLayoutId(): Int = R.layout.fragment_dashboard

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.myViewModel = viewModel

        setAdapter()
        addBarData()

    }

    private fun addBarData() {
        val dataList: ArrayList<BarData> = ArrayList()
        var data = BarData("10", 3.4f, "3.4€")
        dataList.add(data)
        data = BarData("11", 8f, "8€")
        dataList.add(data)
        data = BarData("12", 1.8f, "1.8€")
        dataList.add(data)
        data = BarData("13", 7.3f, "7.3€")
        dataList.add(data)
        data = BarData("14", 6.2f, "6.2€")
        dataList.add(data)
        data = BarData("15", 3.3f, "3.3€")
        dataList.add(data)
        data = BarData("16", 3.3f, "3.3€")
        dataList.add(data)
        data = BarData("17", 3.3f, "3.3€")
        dataList.add(data)
        data = BarData("18", 3.3f, "3.3€")

        binding.chartProgressBar.setDataList(dataList)
        binding.chartProgressBar.build()
    }

    private fun setAdapter() {
        val gridLayoutManager = GridLayoutManager(getBaseActivity(),3);
        adapter = DashboardTSPAdapter(getBaseActivity())
        binding.rvTsp.layoutManager = gridLayoutManager
        binding.rvTsp.isNestedScrollingEnabled = false
        binding.rvTsp.adapter = adapter
        adapter.setNavigator(this)
    }

    override fun onOrdersClick() {
        startActivity(Intent(getBaseActivity(), SalesOrderListActivity::class.java))
    }

    override fun onNewSalesClick() {

    }

    override fun onCustomersClick() {
        //startActivity(Intent(getBaseActivity(), ContactsActivity::class.java))
        setFragment(CustomersListFragment(), "Customers List", true)
    }

    override fun onInVentoryClick() {
        setFragment(ProductItemsFragment(), "Inventory", true)
    }

    override fun onInVoiceClick() {
        startActivity(Intent(getBaseActivity(), InvoiceTabsActivity::class.java))
        // setFragment(SelectedItemsFragment(), "invoice", true)
    }

    override fun onCreateSales() {
        startActivity(Intent(getBaseActivity(), SalesOrderFormActivity::class.java))
    }

    override fun onItemClick() {
        setFragment(SelectedItemsFragment(), "Selected Item", true)
    }


    private fun setFragment(fragment: Fragment, tag: String, back: Boolean) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.add(R.id.nav_host_fragment, fragment, tag)
        if (back) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

}