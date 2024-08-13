package com.pos.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper

open class HomeViewModel @ViewModelInject constructor(
        private val apiHelper: ApiHelper
) : BaseViewModel<HomeNavigator>() {
}