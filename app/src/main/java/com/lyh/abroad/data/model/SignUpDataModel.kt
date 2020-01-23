package com.lyh.abroad.data.model

import android.net.Uri

data class SignUpDataModel(
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
    val profileUri: Uri,
    /** 사용자 이름*/
    val userName: String
)
