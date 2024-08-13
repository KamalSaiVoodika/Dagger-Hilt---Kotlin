package com.pos.ui.invoice.invoice_overdue

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pos.R
import com.pos.base.BaseViewHolder
import com.pos.databinding.RowInvoiceOverdueBinding
import com.pos.databinding.RowSalesAllOpenBinding

class InvoiceOverdueAdapter(val context: Context) : RecyclerView.Adapter<BaseViewHolder>() {
    lateinit var navigator: InvoiceOverdueNavigator
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.row_invoice_overdue,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = 4

    fun setNavigator(fragment: InvoiceOverdueFragment) {
        navigator = fragment
    }

    inner class MyViewHolder(binding: RowInvoiceOverdueBinding) : BaseViewHolder(binding.root) {

        val binding: RowInvoiceOverdueBinding = binding

        override fun onBind(position: Int) {
            binding.cvMain.setOnClickListener {
                navigator.onItemClick()
            }
        }

    }
}