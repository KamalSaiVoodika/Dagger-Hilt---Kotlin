package com.pos.ui.sales_order_details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pos.ui.sales_accounting.SalesAccountingFragment
import com.pos.ui.sales_content.SalesContentFragment
import com.pos.ui.sales_header.SalesHeaderFragment
import com.pos.ui.sales_logistics.SalesLogisticsFragment


private const val NUM_TABS = 4

public class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var navigator: SalesOrderDetailsNavigator
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return SalesHeaderFragment(navigator)
            1 -> return SalesContentFragment()
            2 -> return SalesLogisticsFragment()
        }
        return SalesAccountingFragment()
    }
}