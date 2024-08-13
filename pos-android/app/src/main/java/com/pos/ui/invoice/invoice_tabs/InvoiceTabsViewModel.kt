package com.pos.ui.invoice.invoice_tabs

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class InvoiceTabsViewModel  @ViewModelInject constructor(
    private val apiHelper: ApiHelper,
) : BaseViewModel<InvoiceTabsNavigator>()