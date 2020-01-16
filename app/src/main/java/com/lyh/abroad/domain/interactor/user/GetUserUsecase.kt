package com.lyh.abroad.domain.interactor.user

import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserUsecase(
    private val userRepository: UserRepository
) : BaseUsecase<UserEntity, Nothing>() {

    override suspend fun bindUsecase(param: Nothing?): ResultModel<UserEntity> =
        withContext(Dispatchers.Main) {
            userRepository.fetchUser()
        }
}
