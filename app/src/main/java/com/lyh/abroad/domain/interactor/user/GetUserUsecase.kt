package com.lyh.abroad.domain.interactor.user

import com.lyh.abroad.data.feed.source.user.UserAuth
import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserUsecase(
    private val userRepository: UserRepository,
    private val userAuth: UserAuth
) : BaseUsecase<UserEntity, GetUserUsecase.UserParam>() {

    data class UserParam(val uid: String) : Param()

    override suspend fun bindUsecase(param: UserParam?): ResultModel<UserEntity> =
        param?.let {
            withContext(Dispatchers.IO) {
                userRepository.fetchUser(param.uid)
            }
        } ?: run {
            withContext(Dispatchers.Main) {
                userAuth.getUserUid().let {
                    if (it.status == ResultModel.Status.SUCCESS && it.data != null) {
                        userRepository.fetchUser(it.data)
                    } else {
                        ResultModel.onFailed()
                    }
                }
            }
        }
}