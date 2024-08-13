package com.pos.ui.dashboard

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pos.R
import com.pos.base.BaseViewHolder
import com.pos.databinding.RowDashboardTspBinding

class DashboardTSPAdapter(val context: Context) : RecyclerView.Adapter<BaseViewHolder>() {
    lateinit var navigator: DashboardNavigator
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.row_dashboard_tsp,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = 5

    fun setNavigator(fragment: DashboardFragment) {
        navigator = fragment
    }

    inner class MyViewHolder(binding: RowDashboardTspBinding) : BaseViewHolder(binding.root) {

        val binding: RowDashboardTspBinding = binding

        override fun onBind(position: Int) {
            binding.tvName.isSelected = true;
            binding.tvName.marqueeRepeatLimit = -1;
            binding.tvName.ellipsize = TextUtils.TruncateAt.MARQUEE;
            binding.cvMain.setOnClickListener {
                navigator.onItemClick()
            }
        }

    }
}