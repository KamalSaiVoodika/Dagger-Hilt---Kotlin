package com.pos.ui.sales_order_form

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class SalesOrderFormViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper,
) : BaseViewModel<SalesOrderFormNavigator>()