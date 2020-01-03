package com.lyh.abroad.domain.interactor

abstract class BaseUsecase<T, P: BaseUsecase.Param> {

    open class Param

    protected abstract suspend fun bindUsecase(param: P? = null): T?

    open suspend fun execute(param: P? = null): T? = bindUsecase(param)

}
