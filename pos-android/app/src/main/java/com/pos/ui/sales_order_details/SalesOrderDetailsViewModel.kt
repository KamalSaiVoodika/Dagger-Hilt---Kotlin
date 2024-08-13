package com.pos.ui.sales_order_details

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class SalesOrderDetailsViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper,
) : BaseViewModel<SalesOrderDetailsNavigator>()