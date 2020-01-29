package com.lyh.abroad.domain.repository

import com.lyh.abroad.domain.model.ResultModel

interface AuthRepository {

    suspend fun fetchNewUserId(email: String, password: String): ResultModel<String>

    suspend fun fetchAuth(email: String, password: String): ResultModel<String>

    suspend fun fetchFmcToken(): ResultModel<String>

    suspend fun fetchId(): ResultModel<String>

}
