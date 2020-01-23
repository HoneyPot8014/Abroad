package com.lyh.abroad.domain.interactor.user

import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserUsecase(
    private val userRepository: UserRepository
) : BaseUsecase<UserEntity, GetUserUsecase.GetUserParam>() {

    data class GetUserParam(val uid: String) : Param()

    override suspend fun bindUsecase(param: GetUserParam?): ResultModel<UserEntity> =
        param?.let {
            withContext(Dispatchers.Main) {
                userRepository.fetchUser(it.uid)
            }
        } ?: withContext(Dispatchers.Main) {
            userRepository.fetchUser()
        }

}
