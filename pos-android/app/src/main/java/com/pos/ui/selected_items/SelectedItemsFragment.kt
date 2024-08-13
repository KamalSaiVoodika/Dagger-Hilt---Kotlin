package com.pos.ui.selected_items

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.pos.R
import com.pos.base.BaseFragment
import com.pos.data.api.ApiHelper
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.FragmentSelectedItemsBinding
import com.pos.ui.dashboard.DashboardTSPAdapter
import com.pos.utils.IOnBackPressed
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SelectedItemsFragment : BaseFragment<FragmentSelectedItemsBinding, SelectedItemsViewModel>(),
    SelectedItemsNavigator, IOnBackPressed {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: SelectedItemsViewModel? = null

    lateinit var binding: FragmentSelectedItemsBinding

    lateinit var adapter: SelectedItemsAdapter

    override fun getViewModel(): SelectedItemsViewModel {
        viewModel = ViewModelProvider(this).get(SelectedItemsViewModel::class.java)
        return viewModel as SelectedItemsViewModel
    }

    override fun getBindingVariable(): Int = BR._all

    override fun getLayoutId(): Int = R.layout.fragment_selected_items

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.selectedItemsModel = viewModel
        setAdapter()
    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(getBaseActivity())
        adapter = SelectedItemsAdapter(getBaseActivity()!!)
        binding.rcvItems.layoutManager = layoutManager
        binding.rcvItems.isNestedScrollingEnabled = false
        binding.rcvItems.adapter = adapter
    }

    override fun onBackPressed() {
        parentFragmentManager.popBackStack()
    }

    override fun onAddMoreClick() {
        initializeBottomSheet()
    }

    // Init the bottom sheet behavior
    private fun initializeBottomSheet() {

        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(getBaseActivity())

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)

        // on below line we are creating a variable for our button
        // which we are using to dismiss our dialog.
        val ivClose = view.findViewById<ImageView>(R.id.iv_close)

        // on below line we are adding on click listener
        // for our dismissing the dialog button.
        ivClose.setOnClickListener {
            // on below line we are calling a dismiss
            // method to close our dialog.
            dialog.dismiss()
        }
        // below line is use to set cancelable to avoid
        // closing of dialog box when clicking on the screen.
        dialog.setCancelable(true)

        // on below line we are setting
        // content view to our view.
        dialog.setContentView(view)

        // on below line we are calling
        // a show method to display a dialog.
        dialog.show()
    }


}