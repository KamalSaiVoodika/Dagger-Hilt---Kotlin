package com.pos.ui.sales_order_list

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper
import com.pos.ui.sales_order_details.SalesOrderDetailsNavigator

class SalesOrderListViewModel  @ViewModelInject constructor(
    private val apiHelper: ApiHelper,
) : BaseViewModel<SalesOrderListNavigator>()