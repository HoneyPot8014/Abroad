package com.lyh.abroad.domain.interactor.user

import com.lyh.abroad.domain.entity.SignUpEntity
import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.exception.AppException
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.model.ResultModel.Status.SUCCESS
import com.lyh.abroad.domain.repository.AuthRepository
import com.lyh.abroad.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignUpUsecase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : BaseUsecase<Unit, SignUpUsecase.SignUpParam>() {

    data class SignUpParam(val signUpEntity: SignUpEntity) : Param()

    override suspend fun bindUsecase(param: SignUpParam?) =
        param?.signUpEntity?.run {
            withContext(Dispatchers.Main) {
                val uid = authRepository.fetchNewUserId(email, password).data
                    ?: return@withContext ResultModel.onFailed<Unit>()
                val pushToken = authRepository.fetchFmcToken().data
                    ?: return@withContext ResultModel.onFailed<Unit>()
                val profileUrl = userRepository.setProfile(uid, profileUri).data
                    ?: return@withContext ResultModel.onFailed<Unit>()
                userRepository.setUser(
                    UserEntity(
                        countryCode,
                        currentCountry,
                        planContents,
                        pushToken,
                        uid,
                        email,
                        profileUrl,
                        userName
                    )
                ).run {
                    if (status == SUCCESS) {
                        ResultModel.onSuccess(Unit)
                    } else {
                        ResultModel.onFailed()
                    }
                }
            }
        } ?: ResultModel.onFailed(AppException.NullParamException())
}
