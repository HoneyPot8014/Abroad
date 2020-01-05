package com.lyh.abroad.domain.interactor

import com.lyh.abroad.domain.model.ResultModel

abstract class BaseUsecase<T, P: BaseUsecase.Param> {

    open class Param

    protected abstract suspend fun bindUsecase(param: P? = null): ResultModel<T>

    open suspend fun execute(param: P? = null): ResultModel<T> = bindUsecase(param)

}
