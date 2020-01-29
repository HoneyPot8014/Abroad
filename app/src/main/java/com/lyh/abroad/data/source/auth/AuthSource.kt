package com.lyh.abroad.data.source.auth

import com.lyh.abroad.domain.model.ResultModel

interface AuthSource {

    suspend fun fetchNewUserId(email: String, password: String): ResultModel<String>

    suspend fun fetchAuth(email: String, password: String): ResultModel<String>

    suspend fun fetchFmcToken(): ResultModel<String>

    suspend fun fetchId(): ResultModel<String>

}
