package com.lyh.abroad.domain.entity

data class UserEntity(
    /** 국가 코드*/
    val countryCode: String,
    /** 도시*/
    val currentCountry: String,
    /** 자기소개*/
    val planContent: String,
    /** FMC 토큰*/
    val pushToken: String,
    /** Firebase Uid*/
    val uid: String,
    /** email Id*/
    val userId: String,
    /** 프로필 url*/
    val userImageUrl: String,
    /** 사용자 이름*/
    val userName: String
)
