package com.pos.ui.sales_order_list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pos.ui.sales_all.SalesAllFragment
import com.pos.ui.sales_approval.SalesApprovalFragment
import com.pos.ui.sales_open.SalesOpenFragment


private const val NUM_TABS = 3

public class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var listNavigator: SalesOrderListNavigator
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return SalesOpenFragment(listNavigator)
            1 -> return SalesAllFragment(listNavigator)
        }
        return SalesApprovalFragment(listNavigator)
    }
}