package com.lyh.abroad.presenter.user

import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.R
import com.lyh.abroad.domain.interactor.user.SignInUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.presenter.base.BaseViewModel
import kotlinx.coroutines.launch

class SignInViewModel (
    private val signInUsecase: SignInUsecase
): BaseViewModel() {

    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()

    fun signIn() {
        val email = emailLiveData.value
        val password = passwordLiveData.value
        when {
            email.isNullOrEmpty() -> _statusLiveData.value =
                Status.Failed(SignInFailReason.EmptyEmail)
            email.isBlank() || email.contains(" ") -> _statusLiveData.value =
                Status.Failed(SignInFailReason.WrongEmail)
            !email.contains("@") || !email.contains(".") -> _statusLiveData.value =
                Status.Failed(SignInFailReason.WrongEmail)
            password.isNullOrEmpty() -> _statusLiveData.value =
                Status.Failed(SignInFailReason.EmptyPassword)
            password.isBlank() || password.contains(" ") -> _statusLiveData.value =
                Status.Failed(SignInFailReason.WrongPassword)
            password.length < 6 -> _statusLiveData.value =
                Status.Failed(SignInFailReason.ShortPassword)
            else -> {
                _statusLiveData.value = Status.Loading
                viewModelScope.launch {
                    signInUsecase.execute(SignInUsecase.LogInParam(email, password)).run {
                        if (status == ResultModel.Status.SUCCESS) {
                            _statusLiveData.value = Status.Success
                        } else {
                            _statusLiveData.value =
                                Status.Failed(SignInFailReason.NoAuth)
                        }
                    }
                }
            }
        }
    }

    sealed class SignInFailReason(message: Int): Reason(message) {
        object EmptyEmail : SignInFailReason(R.string.empty_email)
        object EmptyPassword : SignInFailReason(R.string.empty_password)
        object WrongEmail : SignInFailReason(R.string.wrong_email)
        object WrongPassword : SignInFailReason(R.string.wrong_password)
        object ShortPassword : SignInFailReason(R.string.short_password)
        object NoAuth : SignInFailReason(R.string.no_auth)
    }
}
