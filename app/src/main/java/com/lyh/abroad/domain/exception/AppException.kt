package com.lyh.abroad.domain.exception

sealed class AppException {
    class NullParamException: Exception("usecase param is null")
    class NoAuthException: Exception("user is not registered")
}
