package com.lyh.abroad.domain.interactor.feed

import com.lyh.abroad.domain.entity.FeedEntity
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.repository.FeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFeedUsecase(
    private val feedRepository: FeedRepository
): BaseUsecase<FeedEntity>() {

    override suspend fun bindUsecase(param: Param?): FeedEntity {
        return withContext(Dispatchers.IO) {
            feedRepository.fetchFeed()
        }
    }
}
