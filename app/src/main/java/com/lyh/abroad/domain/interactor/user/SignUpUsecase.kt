package com.lyh.abroad.domain.interactor.user

import com.lyh.abroad.domain.entity.SignUpEntity
import com.lyh.abroad.domain.exception.AppException
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignUpUsecase(
    private val userRepository: UserRepository
) : BaseUsecase<Unit, SignUpUsecase.SignUpParam>() {

    data class SignUpParam(val signUpEntity: SignUpEntity) : Param()

    override suspend fun bindUsecase(param: SignUpParam?) =
        param?.let {
            withContext(Dispatchers.IO) {
                userRepository.setUserWithSignUp(param.signUpEntity)
            }
        } ?: ResultModel.onFailed(AppException.NullParamException())

}
