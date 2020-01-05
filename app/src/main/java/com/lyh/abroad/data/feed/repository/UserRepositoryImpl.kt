package com.lyh.abroad.data.feed.repository

import com.lyh.abroad.data.feed.mapper.UserDataMapper
import com.lyh.abroad.data.feed.source.user.UserAuth
import com.lyh.abroad.data.feed.source.user.UserSource
import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.exception.AppException
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.model.ResultModel.Status.SUCCESS
import com.lyh.abroad.domain.repository.UserRepository

class UserRepositoryImpl private constructor(
    private val userAuth: UserAuth,
    private val userRemoteSource: UserSource
) : UserRepository {

    companion object {

        private var INSTANCE: UserRepositoryImpl? = null

        fun getInstance(userAuth: UserAuth, userRemoteSource: UserSource): UserRepositoryImpl =
            INSTANCE ?: kotlin.run {
                UserRepositoryImpl(userAuth, userRemoteSource).also {
                    INSTANCE = it
                }
            }
    }

    override suspend fun fetchUser(uid: String): ResultModel<UserEntity> =
        userRemoteSource.fetchUser(uid).let {
            if (it.status == SUCCESS) {
                ResultModel.onSuccess(UserDataMapper.toEntity(it.data))
            } else {
                ResultModel.onFailed(it.error)
            }
        }

    override suspend fun fetchUserWithLogIn(email: String, password: String): ResultModel<UserEntity> {
        return userAuth.signIn(email, password).let {
            if (it.status == SUCCESS) {
                it.data?.user?.uid?.let {uid ->
                    fetchUser(uid)
                } ?: ResultModel.onFailed(AppException.NoAuthException())
            } else {
                ResultModel.onFailed(it.error)
            }
        }
    }

    override suspend fun setUser(userEntity: UserEntity) {

    }

}
