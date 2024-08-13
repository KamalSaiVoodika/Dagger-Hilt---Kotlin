package com.pos.ui.sales_logistics

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class SalesLogisticsViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper
) : BaseViewModel<SalesLogisticsNavigator>() {
}