package com.lyh.abroad.presenter.user

import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.R
import com.lyh.abroad.domain.interactor.user.LogInUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.presenter.base.BaseViewModel
import kotlinx.coroutines.launch

class SignViewModel(
    private val logInUsecase: LogInUsecase
): BaseViewModel() {

    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()

    fun signIn() {
        val email = emailLiveData.value
        val password = passwordLiveData.value
        _statusLiveData.value = null
        when {
            email.isNullOrEmpty() -> _statusLiveData.value = Status.Failed(FailReason.EmptyEmail)
            password.isNullOrEmpty() -> _statusLiveData.value = Status.Failed(FailReason.EmptyPassword)
            !email.contains("@") || !email.contains(".") -> {
                _statusLiveData.value = Status.Failed(FailReason.WrongEmail)
            }
            password.length < 6 -> _statusLiveData.value = Status.Failed(FailReason.WrongPassword)
            else -> {
                _statusLiveData.value = Status.Loading
                viewModelScope.launch {
                    logInUsecase.execute(LogInUsecase.LogInParam(email, password)).run {
                        if (status == ResultModel.Status.SUCCESS) {
                            _statusLiveData.value = Status.Success
                        } else {
                            _statusLiveData.value = Status.Failed(FailReason.NoAuth)
                        }
                    }
                }
            }
        }
    }

    sealed class FailReason(message: Int) : Reason(message) {
        object WrongEmail: FailReason(R.string.wrong_email)
        object WrongPassword: FailReason(R.string.wrong_password)
        object EmptyEmail: FailReason(R.string.empty_email)
        object EmptyPassword: FailReason(R.string.empty_password)
        object NoAuth: FailReason(R.string.no_auth)
    }

}
