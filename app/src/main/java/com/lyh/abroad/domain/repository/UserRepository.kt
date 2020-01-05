package com.lyh.abroad.domain.repository

import com.lyh.abroad.domain.entity.UserEntity

interface UserRepository {

    suspend fun fetchUser(uid: String): UserEntity?

    suspend fun setUser(userEntity: UserEntity)

}
