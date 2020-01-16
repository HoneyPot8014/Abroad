package com.lyh.abroad.data.source.user

import android.net.Uri
import com.lyh.abroad.data.model.UserDataModel
import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.model.ResultModel

interface UserSource {

    suspend fun fetchUser(uid: String): ResultModel<UserDataModel>

    suspend fun setUser(userEntity: UserEntity): ResultModel<Unit>

    suspend fun setProfile(uid:String, uri: Uri): ResultModel<String>

}
