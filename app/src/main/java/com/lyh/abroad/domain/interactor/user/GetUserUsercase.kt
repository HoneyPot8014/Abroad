package com.lyh.abroad.domain.interactor.user

import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.exception.AppException
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserUsercase(
    private val userRepository: UserRepository
) : BaseUsecase<UserEntity, GetUserUsercase.UserParam>() {

    data class UserParam(val uid: String) : Param()

    override suspend fun bindUsecase(param: UserParam?): ResultModel<UserEntity> =
        param?.let {
            withContext(Dispatchers.IO) {
                userRepository.fetchUser(param.uid)
            }
        } ?: ResultModel.onFailed(AppException.NullParamException())
}