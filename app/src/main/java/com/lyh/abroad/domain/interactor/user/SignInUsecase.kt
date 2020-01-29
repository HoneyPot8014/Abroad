package com.lyh.abroad.domain.interactor.user

import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.exception.AppException
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.AuthRepository
import com.lyh.abroad.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignInUsecase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : BaseUsecase<UserEntity, SignInUsecase.LogInParam>() {

    data class LogInParam(val email: String, val password: String) : Param()

    override suspend fun bindUsecase(param: LogInParam?): ResultModel<UserEntity> =
        param?.let {
            withContext(Dispatchers.Main) {
                val uid = authRepository.fetchAuth(it.email, it.password).data
                    ?: return@withContext ResultModel.onFailed<UserEntity>()
                userRepository.fetchUser(uid)
            }
        } ?: ResultModel.onFailed(AppException.NullParamException())
}