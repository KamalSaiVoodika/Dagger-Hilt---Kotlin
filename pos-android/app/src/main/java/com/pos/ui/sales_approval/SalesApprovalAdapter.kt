package com.pos.ui.sales_approval

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pos.R
import com.pos.base.BaseViewHolder
import com.pos.databinding.RowSalesAllOpenBinding

class SalesApprovalAdapter(val context: Context) : RecyclerView.Adapter<BaseViewHolder>() {
    lateinit var navigator: SalesApprovalNavigator
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.row_sales_all_open,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = 4

    fun setNavigator(fragment: SalesApprovalFragment) {
        navigator = fragment
    }

    inner class MyViewHolder(binding: RowSalesAllOpenBinding) : BaseViewHolder(binding.root) {

        val binding: RowSalesAllOpenBinding = binding

        override fun onBind(position: Int) {
            binding.cvMain.setOnClickListener {
                navigator.onItemClick()
            }
        }

    }
}