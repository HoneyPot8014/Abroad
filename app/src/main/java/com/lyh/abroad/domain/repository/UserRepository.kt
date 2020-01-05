package com.lyh.abroad.domain.repository

import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.model.ResultModel

interface UserRepository {

    suspend fun fetchUser(uid: String): ResultModel<UserEntity>

    suspend fun fetchUserWithLogIn(email: String, password: String): ResultModel<UserEntity>

    suspend fun setUser(userEntity: UserEntity)

}
