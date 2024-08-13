package com.pos.ui.product_items

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class ProductItemsViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper
) : BaseViewModel<ProductItemsNavigator>() {
}