package com.lyh.abroad.domain.interactor

abstract class NonNullBaseUsecase<T, P: BaseUsecase.Param>: BaseUsecase<T, P>() {

    abstract override suspend fun bindUsecase(param: P?): T

    override suspend fun execute(param: P?): T = bindUsecase(param)

}
