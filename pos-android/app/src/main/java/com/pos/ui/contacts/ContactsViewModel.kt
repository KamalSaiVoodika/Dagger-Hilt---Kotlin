package com.pos.ui.contacts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pos.base.BaseViewModel
import com.pos.data.api.ApiHelper
import com.pos.data.model.BaseResponse
import com.pos.utils.Resource
import kotlinx.coroutines.launch

open class ContactsViewModel @ViewModelInject constructor(
    private val apihelper: ApiHelper,
) : BaseViewModel<ContactsNavigator>() {

    var loginData: MutableLiveData<Resource<BaseResponse>> = MutableLiveData()

    open fun login(email: String, password: String) {
        viewModelScope.launch {
            loginData.postValue(Resource.loading(null))
            apihelper.login(email, password).let {
                if (it.isSuccessful) {
                    if (it.body()?.result == true) {
                        loginData.postValue(Resource.success(it.body()))
                    } else {
                        loginData.postValue(Resource.error(it.body()?.msg.toString(), null))
                    }
                } else {
                    loginData.postValue(Resource.error(it.message(), null))
                }
            }
        }

    }

}