package com.pos.ui.sales_orders

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class SalesOrdersViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper,
) : BaseViewModel<SalesOrdersNavigator>()