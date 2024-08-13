package com.pos.ui.invoice.invoice_all

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class InvoiceAllViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper
) : BaseViewModel<InvoiceAllNavigator>() {
}