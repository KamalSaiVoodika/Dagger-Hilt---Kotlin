package com.pos.ui.invoice.invoice_tabs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.pos.BR
import com.pos.R
import com.pos.base.BaseActivity
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.ActivityInvoiceTabsBinding
import com.pos.databinding.ActivitySalesOrderListBinding
import com.pos.ui.sales_order_details.SalesOrderDetailsActivity
import com.pos.ui.sales_order_form.SalesOrderFormActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InvoiceTabsActivity :
    BaseActivity<ActivityInvoiceTabsBinding, InvoiceTabsViewModel>(),
    InvoiceTabsNavigator {

    val salesArray = arrayOf(
        "Overdue", "Open", "All",
    )


    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: InvoiceTabsViewModel? = null

    lateinit var binding: ActivityInvoiceTabsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        binding.invoiceTabsModel = viewModel
        viewModel?.setNavigator(this)

        setAdapter()
        setTabLayout()
    }

    private fun setAdapter() {
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, this)
        binding.viewPager.adapter = adapter
    }

    private fun setTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = salesArray[position]
        }.attach()
    }


    override fun getViewModel(): InvoiceTabsViewModel {
        viewModel = ViewModelProvider(this).get(InvoiceTabsViewModel::class.java)
        return viewModel as InvoiceTabsViewModel
    }

    override fun getLayoutId(): Int = R.layout.activity_invoice_tabs

    override fun getBindingVariable(): Int = BR._all

    override fun onBackClick() {
        onBackPressed()
    }

    override fun onSalesDetailsClick() {
        startActivity(Intent(this, SalesOrderDetailsActivity::class.java))
    }

    override fun onInvoiceClick() {
        startActivity(Intent(this, SalesOrderDetailsActivity::class.java))
    }

    override fun onNewInvoiceClick() {
        var wrapper: Context = ContextThemeWrapper(this, R.style.CustomPopUpStyle)
        val popup = PopupMenu(wrapper, binding.btnNewInvoice)
        popup.inflate(R.menu.invoice_pop_up_menu)
        binding.btnNewInvoice.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0,
            0,
            R.drawable.ic_baseline_arrow_drop_down_24,
            0
        )
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.select_order -> {
                    onSalesDetailsClick()
                }
                R.id.create_order -> {
                    startActivity(Intent(this, SalesOrderFormActivity::class.java))
                }
            }

            true
        })

        popup.show()

        popup.setOnDismissListener {
            run {
                binding.btnNewInvoice.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_baseline_arrow_drop_up_24,
                    0
                )
            }
        }
    }


}