package com.lyh.abroad.data.feed.source.user

import com.lyh.abroad.data.feed.model.UserDataModel
import com.lyh.abroad.domain.model.ResultModel

interface UserSource {

    suspend fun fetchUser(uid: String): ResultModel<UserDataModel>

    suspend fun setUser(userEntity: UserDataModel)

}
