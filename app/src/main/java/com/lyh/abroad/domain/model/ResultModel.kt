package com.lyh.abroad.domain.model

open class ResultModel<T> private constructor(
    val status: Status = Status.FAILED,
    val data: T? = null,
    val error: Throwable? = null
) {

    companion object {

        fun <T> onSuccess(result: T?): ResultModel<T> =
            ResultModel(
                Status.SUCCESS,
                result,
                null
            )

        fun <T> onFailed(error: Throwable? = null): ResultModel<T> =
            ResultModel(
                Status.FAILED,
                null,
                error
            )
    }

    enum class Status {
        SUCCESS, FAILED
    }
}
