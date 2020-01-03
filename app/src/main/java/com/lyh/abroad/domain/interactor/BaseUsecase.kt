package com.lyh.abroad.domain.interactor

abstract class BaseUsecase<T> {

    class Param

    protected abstract suspend fun bindUsecase(param: Param? = null): T

    suspend fun execute(param: Param? = null): T = bindUsecase(param)

}
