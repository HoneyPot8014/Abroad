package com.lyh.abroad.domain.interactor.user

import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.exception.AppException
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogInUsecase(
    private val userRepository: UserRepository
) : BaseUsecase<UserEntity, LogInUsecase.LogInParam>() {

    data class LogInParam(val email: String, val password: String) : Param()

    override suspend fun bindUsecase(param: LogInParam?): ResultModel<UserEntity> =
        param?.let {
            withContext(Dispatchers.IO) {
                userRepository.fetchUserWithLogIn(it.email, it.password)
            }
        } ?: ResultModel.onFailed(AppException.NullParamException())
}