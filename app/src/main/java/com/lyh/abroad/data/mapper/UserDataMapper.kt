package com.lyh.abroad.data.mapper

import com.lyh.abroad.data.model.UserDataModel
import com.lyh.abroad.domain.entity.UserEntity

object UserDataMapper {

    fun toEntity(userDataModel: UserDataModel?): UserEntity? {
        return if (userDataModel == null || userDataModel.isEmpty()) {
            null
        } else {
            UserEntity(
                userDataModel.countryCode!!,
                userDataModel.currentCountry!!,
                userDataModel.planContents!!,
                userDataModel.pushToken!!,
                userDataModel.uid!!,
                userDataModel.userId!!,
                userDataModel.userImageUrl!!,
                userDataModel.userName!!
            )
        }
    }

    fun toModel(userEntity: UserEntity): UserDataModel =
        UserDataModel(
            userEntity.countryCode,
            userEntity.currentCountry,
            userEntity.planContents,
            userEntity.pushToken,
            userEntity.uid,
            userEntity.userId,
            userEntity.userImageUrl,
            userEntity.userName
        )
}