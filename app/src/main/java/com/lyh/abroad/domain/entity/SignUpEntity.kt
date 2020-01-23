package com.lyh.abroad.domain.entity

data class SignUpEntity(
    /** 이메일 */
    val email: String,
    /** 비밀번호 */
    val password: String,
    /** 국가 코드*/
    val countryCode: String,
    /** 도시*/
    val currentCountry: String,
    /** 자기소개*/
    val planContents: String,
    /** 프로필 url*/
    val profileUri: String,
    /** 사용자 이름*/
    val userName: String
)
