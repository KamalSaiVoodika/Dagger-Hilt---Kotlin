package com.pos.ui.add_customer

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

open class AddCustomerViewModel @ViewModelInject
constructor(
    private val apihelper: ApiHelper,
) : BaseViewModel<AddCustomerNavigator>() {

}