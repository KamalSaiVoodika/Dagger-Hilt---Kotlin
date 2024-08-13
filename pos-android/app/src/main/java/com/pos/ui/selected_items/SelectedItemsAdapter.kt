package com.pos.ui.selected_items

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pos.R
import com.pos.base.BaseViewHolder
import com.pos.databinding.RowSelectedItemsBinding

class SelectedItemsAdapter(val context: Context) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.row_selected_items, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = 3

    inner class MyViewHolder(binding: RowSelectedItemsBinding) : BaseViewHolder(binding.root) {

        val binding: RowSelectedItemsBinding = binding

        override fun onBind(position: Int) {

        }
    }

}