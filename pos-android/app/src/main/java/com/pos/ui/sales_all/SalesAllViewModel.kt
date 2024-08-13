package com.pos.ui.sales_all

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class SalesAllViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper
) : BaseViewModel<SalesAllNavigator>() {
}