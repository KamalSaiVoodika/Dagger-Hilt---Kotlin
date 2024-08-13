package com.pos.ui.invoice.invoice_open

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class InvoiceOpenViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper
) : BaseViewModel<InvoiceOpenNavigator>() {
}