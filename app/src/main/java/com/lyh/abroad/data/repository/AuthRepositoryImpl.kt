package com.lyh.abroad.data.repository

import com.lyh.abroad.data.source.auth.AuthSource
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authSource: AuthSource
) : AuthRepository {

    companion object {

        private var INSTANCE: AuthRepositoryImpl? = null

        fun getInstance(authSource: AuthSource) =
            INSTANCE ?: AuthRepositoryImpl(authSource).also {
                INSTANCE = it
            }
    }

    override suspend fun fetchNewUserId(email: String, password: String): ResultModel<String> =
        authSource.fetchNewUserId(email, password)

    override suspend fun fetchAuth(email: String, password: String): ResultModel<String> =
        authSource.fetchAuth(email, password)

    override suspend fun fetchFmcToken(): ResultModel<String> =
        authSource.fetchFmcToken()

    override suspend fun fetchId(): ResultModel<String> =
        authSource.fetchId()
}
