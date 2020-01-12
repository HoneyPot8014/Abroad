package com.lyh.abroad.presenter.user


import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.R
import com.lyh.abroad.domain.entity.SignUpEntity
import com.lyh.abroad.domain.interactor.user.SignUpUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.base.BaseViewModel.Status.*
import com.lyh.abroad.presenter.model.Country
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUsecase: SignUpUsecase
) : BaseViewModel() {

    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()
    val nameLiveData = MutableLiveData<String>()
    val introLiveData = MutableLiveData<String>()
    val profileUriLiveData = MutableLiveData<Uri>()

    fun signUp(country: Country?) {
        val email = emailLiveData.value
        val password = passwordLiveData.value
        val name = nameLiveData.value
        val intro = introLiveData.value
        val profileUri = profileUriLiveData.value
        when {
            email.isNullOrEmpty() -> _statusLiveData.value = Failed(SignUpFailReason.EmptyEmail)
            email.isBlank() || email.contains(" ") -> _statusLiveData.value =
                Failed(SignUpFailReason.WrongEmail)
            !email.contains("@") || !email.contains(".") -> _statusLiveData.value =
                Failed(SignUpFailReason.WrongEmail)
            password.isNullOrEmpty() -> _statusLiveData.value =
                Failed(SignUpFailReason.EmptyPassword)
            password.isBlank() || password.contains(" ") -> _statusLiveData.value =
                Failed(SignUpFailReason.WrongPassword)
            password.length < 6 -> _statusLiveData.value = Failed(SignUpFailReason.ShortPassword)
            name.isNullOrEmpty() -> _statusLiveData.value = Failed(SignUpFailReason.EmptyName)
            name.isBlank() || name.contains(" ") -> _statusLiveData.value =
                Failed(SignUpFailReason.WrongName)
            intro.isNullOrEmpty() || intro.isBlank() -> _statusLiveData.value =
                Failed(SignUpFailReason.EmptyIntro)
            profileUri == null -> _statusLiveData.value = Failed(SignUpFailReason.EmptyProfile)
            country == null -> _statusLiveData.value = Failed(SignUpFailReason.EmptyNation)
            else -> {
                _statusLiveData.value = Loading
                viewModelScope.launch {
                    signUpUsecase.execute(
                        SignUpUsecase.SignUpParam(
                            SignUpEntity(
                                email,
                                password,
                                country.countryCode,
                                country.countryName,
                                intro,
                                profileUri.toString(),
                                name
                            )
                        )
                    ).run {
                        if (status == ResultModel.Status.SUCCESS) {
                            _statusLiveData.value = Success
                            _statusLiveData.value = null
                        } else {
                            _statusLiveData.value = Failed(SignUpFailReason.NoAuth)
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
            _statusLiveData.value = Failed(SignUpFailReason.FailedProfile)
        }
    }

    sealed class SignUpFailReason(message: Int) : Reason(message) {
        object WrongEmail : SignUpFailReason(R.string.wrong_email)
        object WrongPassword : SignUpFailReason(R.string.wrong_password)
        object WrongName : SignUpFailReason(R.string.wrong_name)
        object ShortPassword : SignUpFailReason(R.string.short_password)
        object WrongIntro : SignUpFailReason(R.string.wrong_intro)
        object EmptyEmail : SignUpFailReason(R.string.empty_email)
        object EmptyPassword : SignUpFailReason(R.string.empty_password)
        object EmptyName : SignUpFailReason(R.string.empty_name)
        object EmptyIntro : SignUpFailReason(R.string.empty_intro)
        object EmptyProfile : SignUpFailReason(R.string.empty_profile)
        object EmptyNation: SignUpFailReason(R.string.empty_nation)
        object NoAuth : SignUpFailReason(R.string.no_auth)
        object FailedProfile : SignUpFailReason(R.string.gallery_error)
    }
}
