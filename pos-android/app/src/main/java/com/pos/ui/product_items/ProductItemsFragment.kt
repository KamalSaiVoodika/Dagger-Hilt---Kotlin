package com.pos.ui.product_items

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pos.BR
import com.pos.R
import com.pos.base.BaseFragment
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.FragmentProductItemsBinding
import com.pos.ui.selected_items.SelectedItemsFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProductItemsFragment : BaseFragment<FragmentProductItemsBinding, ProductItemsViewModel>(),
    ProductItemsNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: ProductItemsViewModel? = null

    lateinit var binding: FragmentProductItemsBinding

    lateinit var adapter: ProductItemsAdapter

    override fun getViewModel(): ProductItemsViewModel {
        viewModel = ViewModelProvider(this).get(ProductItemsViewModel::class.java)
        return viewModel as ProductItemsViewModel
    }

    override fun getBindingVariable(): Int = BR._all

    override fun getLayoutId(): Int = R.layout.fragment_product_items

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.myViewModel = viewModel

        setAdapter()

    }

    private fun setAdapter() {
        val layoutManager = LinearLayoutManager(getBaseActivity())
        adapter = ProductItemsAdapter(getBaseActivity())
        binding.rvTsp.layoutManager = layoutManager
        binding.rvTsp.isNestedScrollingEnabled = false
        binding.rvTsp.adapter = adapter
        adapter.setNavigator(this)
    }

    override fun onBackClick() {
        parentFragmentManager.popBackStack()
    }

    override fun onItemClick() {
        setFragment(SelectedItemsFragment(), "Selected Item", true)
    }

    private fun setFragment(fragment: Fragment, tag: String, back: Boolean) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.add(R.id.nav_host_fragment, fragment, tag)
        if (back) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

}