package com.pos.ui.customers_list

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class CustomersListViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper
) : BaseViewModel<CustomersListNavigator>() {
}