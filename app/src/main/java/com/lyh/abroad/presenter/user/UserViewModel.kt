package com.lyh.abroad.presenter.user

import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.interactor.user.GetUserUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.presenter.base.BaseViewModel
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUserUsecase: GetUserUsecase
): BaseViewModel() {

    private val _userLiveData = MutableLiveData<UserEntity>()
    val userLiveData
        get() = _userLiveData

    init {
        viewModelScope.launch {
            _statusLiveData.value = Status.Loading
            getUserUsecase.execute().let {
                if (it.status == ResultModel.Status.SUCCESS) {
                    _userLiveData.value = it.data
                } else {
                    _statusLiveData.value = Status.Failed(UserFailReason.LoginFailed)
                }
            }
        }
    }

    sealed class UserFailReason(message: Int) : Reason(message) {
        object LoginFailed: UserFailReason(0)
    }

}
