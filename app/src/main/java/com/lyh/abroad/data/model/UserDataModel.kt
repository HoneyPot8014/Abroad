package com.lyh.abroad.data.model

data class UserDataModel(
    /** 국가 코드*/
    val countryCode: String? = null,
    /** 도시*/
    val currentCountry: String? = null,
    /** 자기소개*/
    val planContents: String? = null,
    /** FMC 토큰*/
    val pushToken: String? = null,
    /** Firebase Uid*/
    val uid: String? = null,
    /** email Id*/
    val userId: String? = null,
    /** 프로필 url*/
    val userImageUrl: String? = null,
    /** 사용자 이름*/
    val userName: String? = null
) {
    fun isEmpty(): Boolean =
        countryCode == null ||
                currentCountry == null ||
                planContents == null ||
                pushToken == null ||
                uid == null ||
                userId == null ||
                userImageUrl == null ||
                userName == null
}
