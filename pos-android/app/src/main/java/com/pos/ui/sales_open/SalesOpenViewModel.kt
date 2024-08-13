package com.pos.ui.sales_open

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class SalesOpenViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper
) : BaseViewModel<SalesOpenNavigator>() {
}