package com.lyh.abroad.domain.interactor.feed

import com.lyh.abroad.domain.entity.FeedEntity
import com.lyh.abroad.domain.exception.AppException
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.FeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFeedUsecase(
    private val feedRepository: FeedRepository
) : BaseUsecase<List<FeedEntity>, GetFeedUsecase.FeedParam>() {

    data class FeedParam(val countryCode: String, val country: String) : Param()

    override suspend fun bindUsecase(param: FeedParam?): ResultModel<List<FeedEntity>> =
        param?.let {
            withContext(Dispatchers.IO) {
                feedRepository.fetchFeedList(param.countryCode, param.country)
            }
        } ?: ResultModel.onFailed(AppException.NullParamException())
}
