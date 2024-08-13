package com.pos.ui.home

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pos.BR
import com.pos.R
import com.pos.base.BaseActivity
import com.pos.data.local.AppPreferencesHelper
import com.pos.databinding.ActivityHomeBinding
import com.pos.ui.dashboard.DashboardFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeNavigator {

    @Inject
    lateinit var helper: AppPreferencesHelper

    private var viewModel: HomeViewModel? = null

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewDataBinding()
        viewModel?.setNavigator(this)
        binding.myViewModel = viewModel

        setFragment(DashboardFragment(), "Dashboard", false)

    }

    override fun getViewModel(): HomeViewModel {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return viewModel as HomeViewModel
    }

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun getBindingVariable(): Int = BR._all

    override fun onSideMenuClick() {
        toggleDrawer()
    }

    private fun toggleDrawer() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun setFragment(fragment: Fragment, tag: String, back: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment, tag)
        if (back) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }
}