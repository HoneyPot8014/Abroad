package com.lyh.abroad.data.feed.mapper

import com.lyh.abroad.domain.entity.SignUpEntity
import com.lyh.abroad.domain.entity.UserEntity

object SignUpToUserMapper {

    fun toEntity(signUpEntity: SignUpEntity, uid: String, url: String): UserEntity {
        return UserEntity(
            signUpEntity.countryCode,
            signUpEntity.currentCountry,
            signUpEntity.planContent,
            "",
            uid,
            signUpEntity.email,
            url,
            signUpEntity.userName
        )
    }
}