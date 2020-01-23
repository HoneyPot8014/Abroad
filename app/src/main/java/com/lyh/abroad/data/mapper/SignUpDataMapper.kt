package com.lyh.abroad.data.mapper

import android.net.Uri
import com.lyh.abroad.data.model.SignUpDataModel
import com.lyh.abroad.domain.entity.SignUpEntity

object SignUpDataMapper {

    fun toEntity() {
        TODO()
    }

    fun toModel(signUpEntity: SignUpEntity): SignUpDataModel =
        SignUpDataModel(
            signUpEntity.email,
            signUpEntity.password,
            signUpEntity.countryCode,
            signUpEntity.currentCountry,
            signUpEntity.planContents,
            Uri.parse(signUpEntity.profileUri),
            signUpEntity.userName
        )
}