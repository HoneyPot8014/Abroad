package com.lyh.abroad.domain.repository

import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.model.ResultModel

interface UserRepository {

    suspend fun fetchUser(uid: String): ResultModel<UserEntity>

    suspend fun setUser(userEntity: UserEntity): ResultModel<Unit>

    suspend fun setProfile(uid: String, uri: String): ResultModel<String>

}
