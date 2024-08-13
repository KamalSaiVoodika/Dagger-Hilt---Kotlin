package com.pos.ui.sales_approval

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class SalesApprovalViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper
) : BaseViewModel<SalesApprovalNavigator>() {
}