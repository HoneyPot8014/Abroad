package com.lyh.abroad.presenter.user

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.R
import com.lyh.abroad.domain.entity.SignUpEntity
import com.lyh.abroad.domain.interactor.user.SignInUsecase
import com.lyh.abroad.domain.interactor.user.SignUpUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.base.BaseViewModel.Status.*
import com.lyh.abroad.presenter.user.SignViewModel.FailReason.*
import kotlinx.coroutines.launch

class SignViewModel(
    private val signInUsecase: SignInUsecase,
    private val signUpUsecase: SignUpUsecase
) : BaseViewModel() {

    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()
    val nameLiveData = MutableLiveData<String>()
    val introLiveData = MutableLiveData<String>()
    val profileUriLiveData = MutableLiveData<Uri>()

    fun signIn() {
        val email = emailLiveData.value
        val password = passwordLiveData.value
        when {
            !isValidEmailPassword(email, password) -> {
                _statusLiveData.value = null
                return
            }
            else -> {
                _statusLiveData.value = Loading
                viewModelScope.launch {
                    signInUsecase.execute(SignInUsecase.LogInParam(email!!, password!!)).run {
                        if (status == ResultModel.Status.SUCCESS) {
                            _statusLiveData.value = Success
                            _statusLiveData.value = null
                        } else {
                            _statusLiveData.value = Failed(NoAuth)
                            _statusLiveData.value = null
                        }
                    }
                }
            }
        }
    }

    fun signUp() {
        val email = emailLiveData.value
        val password = passwordLiveData.value
        val name = nameLiveData.value
        val intro = introLiveData.value
        val profileUri = profileUriLiveData.value
        when {
            !isValidEmailPassword(email, password) -> {
                _statusLiveData.value = null
                return
            }
            name.isNullOrEmpty() -> _statusLiveData.value = Failed(EmptyName)
            name.isBlank() || name.contains(" ") -> _statusLiveData.value = Failed(WrongName)
            intro.isNullOrEmpty() || intro.isBlank() -> _statusLiveData.value = Failed(EmptyIntro)
            profileUri == null -> _statusLiveData.value = Failed(EmptyProfile)
            else -> {
                _statusLiveData.value = Loading
                viewModelScope.launch {
                    signUpUsecase.execute(SignUpUsecase.SignUpParam(SignUpEntity(
                        email!!, password!!, "KR", "South Korea", intro, profileUri.toString(), name
                    ))).run {
                        if (status == ResultModel.Status.SUCCESS) {
                            _statusLiveData.value = Success
                            _statusLiveData.value = null
                        } else {
                            _statusLiveData.value = Failed(NoAuth)
                            _statusLiveData.value = null
                        }
                    }
                }
            }
        }
    }

    fun setProfileUri(uri: Uri?) {
        uri?.also {
            profileUriLiveData.value = it
        } ?: kotlin.run {
            _statusLiveData.value = Failed(FailedProfile)
        }
    }

    private fun isValidEmailPassword(email: String?, password: String?) =
        when {
            email.isNullOrEmpty() -> {
                _statusLiveData.value = Failed(EmptyEmail)
                false
            }
            email.isBlank() || email.contains(" ") -> {
                _statusLiveData.value = Failed(WrongEmail)
                false
            }
            !email.contains("@") || !email.contains(".") -> {
                _statusLiveData.value = Failed(WrongEmail)
                false
            }
            password.isNullOrEmpty() -> {
                _statusLiveData.value = Failed(EmptyPassword)
                false
            }
            password.isBlank() || password.contains(" ") -> {
                _statusLiveData.value = Failed(WrongPassword)
                false
            }
            password.length < 6 -> {
                _statusLiveData.value = Failed(ShortPassword)
                false
            }
            else -> true
        }

    sealed class FailReason(message: Int) : Reason(message) {
        object WrongEmail : FailReason(R.string.wrong_email)
        object WrongPassword : FailReason(R.string.wrong_password)
        object WrongName: FailReason(R.string.wrong_name)
        object ShortPassword : FailReason(R.string.short_password)
        object WrongIntro : FailReason(R.string.wrong_intro)
        object EmptyEmail : FailReason(R.string.empty_email)
        object EmptyPassword : FailReason(R.string.empty_password)
        object EmptyName : FailReason(R.string.empty_name)
        object EmptyIntro : FailReason(R.string.empty_intro)
        object EmptyProfile : FailReason(R.string.empty_profile)
        object NoAuth : FailReason(R.string.no_auth)
        object FailedProfile : FailReason(R.string.gallery_error)
    }

}
