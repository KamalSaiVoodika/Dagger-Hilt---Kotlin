package com.pos.ui.invoice.invoice_tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pos.ui.invoice.invoice_all.InvoiceAllFragment
import com.pos.ui.invoice.invoice_open.InvoiceOpenFragment
import com.pos.ui.invoice.invoice_overdue.InvoiceOverdueFragment


private const val NUM_TABS = 3

public class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var listNavigator: InvoiceTabsNavigator
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return InvoiceOverdueFragment(listNavigator)
            1->return InvoiceOpenFragment(listNavigator)
            2-> return InvoiceAllFragment(listNavigator)
        }
        return InvoiceAllFragment(listNavigator)
    }
}