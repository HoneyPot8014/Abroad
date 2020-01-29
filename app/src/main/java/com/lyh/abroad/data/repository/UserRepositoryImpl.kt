package com.lyh.abroad.data.repository

import android.net.Uri
import com.lyh.abroad.data.mapper.UserDataMapper
import com.lyh.abroad.data.source.user.UserSource
import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.model.ResultModel.Status.SUCCESS
import com.lyh.abroad.domain.repository.UserRepository

class UserRepositoryImpl private constructor(
    private val userRemoteSource: UserSource
) : UserRepository {

    private val cache = mutableMapOf<String, UserEntity>()

    companion object {

        private var INSTANCE: UserRepositoryImpl? = null

        fun getInstance(userRemoteSource: UserSource): UserRepositoryImpl =
            INSTANCE ?: kotlin.run {
                UserRepositoryImpl(userRemoteSource).also {
                    INSTANCE = it
                }
            }
    }

    override suspend fun fetchUser(uid: String): ResultModel<UserEntity> =
        userRemoteSource.fetchUser(uid).let { userModel ->
            if (userModel.status == SUCCESS) {
                UserDataMapper.toEntity(userModel.data)?.let { userEntity ->
                    cache[uid] = userEntity
                    ResultModel.onSuccess(userEntity)
                } ?: ResultModel.onFailed()
            } else {
                ResultModel.onFailed(userModel.error)
            }
        }

    override suspend fun setUser(userEntity: UserEntity): ResultModel<Unit> =
        userRemoteSource.setUser(userEntity)

    override suspend fun setProfile(uid: String, uri: String): ResultModel<String> =
        userRemoteSource.setProfile(uid, Uri.parse(uri))

}
