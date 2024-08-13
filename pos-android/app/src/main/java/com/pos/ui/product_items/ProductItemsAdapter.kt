package com.pos.ui.product_items

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pos.R
import com.pos.base.BaseViewHolder
import com.pos.databinding.RowDashboardTspBinding
import com.pos.databinding.RowProductItemsBinding
import com.pos.ui.dashboard.DashboardFragment
import com.pos.ui.dashboard.DashboardNavigator

class ProductItemsAdapter(val context: Context) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var navigator: ProductItemsNavigator

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.row_product_items,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = 5

    fun setNavigator(fragment: ProductItemsFragment) {
        navigator = fragment
    }

    inner class MyViewHolder(binding: RowProductItemsBinding) : BaseViewHolder(binding.root) {

        val binding: RowProductItemsBinding = binding

        override fun onBind(position: Int) {
            binding.cvMain.setOnClickListener {
                navigator.onItemClick()
            }
        }

    }
}