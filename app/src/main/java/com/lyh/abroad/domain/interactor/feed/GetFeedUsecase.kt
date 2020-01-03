package com.lyh.abroad.domain.interactor.feed

import com.lyh.abroad.domain.entity.FeedEntity
import com.lyh.abroad.domain.interactor.NonNullBaseUsecase
import com.lyh.abroad.domain.repository.FeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFeedUsecase(
    private val feedRepository: FeedRepository
) : NonNullBaseUsecase<List<FeedEntity>, GetFeedUsecase.FeedParam>() {

    data class FeedParam(val countryCode: String, val country: String) : Param()

    override suspend fun bindUsecase(param: FeedParam?): List<FeedEntity> =
        param?.let {
            withContext(Dispatchers.IO) {
                feedRepository.fetchFeedList(param.countryCode, param.country)
            }
        } ?: emptyList()
}
