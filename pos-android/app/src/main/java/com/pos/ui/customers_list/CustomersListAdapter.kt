package com.pos.ui.customers_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pos.R
import com.pos.base.BaseViewHolder
import com.pos.databinding.RowCustomersListBinding
import com.pos.databinding.RowSalesAllOpenBinding
import com.pos.databinding.RowSalesContentBinding
import com.pos.ui.sales_approval.SalesApprovalFragment
import com.pos.ui.sales_approval.SalesApprovalNavigator

class CustomersListAdapter(val context: Context) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var navigator: CustomersListNavigator

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_customers_list,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = 5


    fun setNavigator(fragment: CustomersListFragment) {
        navigator = fragment
    }

    inner class MyViewHolder(binding: RowCustomersListBinding) : BaseViewHolder(binding.root) {

        val binding: RowCustomersListBinding = binding

        override fun onBind(position: Int) {

        }

    }
}