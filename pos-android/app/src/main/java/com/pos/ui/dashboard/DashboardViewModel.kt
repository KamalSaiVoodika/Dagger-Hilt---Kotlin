package com.pos.ui.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class DashboardViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper
) : BaseViewModel<DashboardNavigator>() {
}