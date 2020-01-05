package com.lyh.abroad.data.feed.repository

import com.lyh.abroad.data.feed.mapper.UserDataMapper
import com.lyh.abroad.data.feed.source.user.UserSource
import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.repository.UserRepository

class UserRepositoryImpl (
    private val userRemoteSource: UserSource
): UserRepository {

    override suspend fun fetchUser(uid: String): UserEntity? =
        userRemoteSource.fetchUser(uid)?.let {
            UserDataMapper.toEntity(it)
        }

    override suspend fun setUser(userEntity: UserEntity) {

    }

}
