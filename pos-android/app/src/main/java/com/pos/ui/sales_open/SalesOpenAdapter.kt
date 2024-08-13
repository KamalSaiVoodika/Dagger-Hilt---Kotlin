package com.pos.ui.sales_open

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pos.R
import com.pos.base.BaseViewHolder
import com.pos.databinding.RowSalesAllOpenBinding

class SalesOpenAdapter(val context: Context) : RecyclerView.Adapter<BaseViewHolder>() {
    lateinit var navigator: SalesOpenNavigator
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

    fun setNavigator(fragment: SalesOpenFragment) {
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