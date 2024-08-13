package com.pos.ui.sales_content

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class SalesContentViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper
) : BaseViewModel<SalesContentNavigator>() {
}