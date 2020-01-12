package com.lyh.abroad.presenter.base

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel() {

    protected val viewModelScope = CoroutineScope(Dispatchers.Main)
    protected val _statusLiveData = LiveEvent<Status>()
    val statusLiveData
        get() = _statusLiveData

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    open class Reason(@StringRes val message: Int)

    sealed class Status {
        object Success : Status()
        object Loading : Status()
        class Failed(val reason: Reason) : Status()
    }

}
