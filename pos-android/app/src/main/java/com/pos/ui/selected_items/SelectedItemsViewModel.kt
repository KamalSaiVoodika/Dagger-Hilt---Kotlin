package com.pos.ui.selected_items

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

class SelectedItemsViewModel @ViewModelInject constructor(
    private val apiHelper: ApiHelper
) : BaseViewModel<SelectedItemsNavigator>()