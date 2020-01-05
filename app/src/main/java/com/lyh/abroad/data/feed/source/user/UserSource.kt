package com.lyh.abroad.data.feed.source.user

import com.lyh.abroad.data.feed.model.UserDataModel

interface UserSource {

    suspend fun fetchUser(uid: String): UserDataModel?

    suspend fun setUser(userEntity: UserDataModel)

}
