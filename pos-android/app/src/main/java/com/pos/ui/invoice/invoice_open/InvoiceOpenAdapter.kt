package com.pos.ui.invoice.invoice_open

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pos.R
import com.pos.base.BaseViewHolder
import com.pos.databinding.RowInvoiceAllBinding

class InvoiceOpenAdapter(val context: Context) : RecyclerView.Adapter<BaseViewHolder>() {
    lateinit var navigator: InvoiceOpenNavigator
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.row_invoice_all,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = 4

    fun setNavigator(fragment: InvoiceOpenFragment) {
        navigator = fragment
    }

    inner class MyViewHolder(binding: RowInvoiceAllBinding) : BaseViewHolder(binding.root) {

        val binding: RowInvoiceAllBinding = binding

        override fun onBind(position: Int) {
            binding.cvMain.setOnClickListener {
                navigator.onItemClick()
            }
        }

    }
}