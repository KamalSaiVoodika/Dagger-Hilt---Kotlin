package com.pos.ui.invoice.invoice_overdue

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class InvoiceOverdueViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper
) : BaseViewModel<InvoiceOverdueNavigator>() {
}