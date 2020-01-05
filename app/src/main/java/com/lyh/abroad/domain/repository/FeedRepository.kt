package com.lyh.abroad.domain.repository

import com.lyh.abroad.domain.entity.FeedEntity
import com.lyh.abroad.domain.model.ResultModel

interface FeedRepository {

    suspend fun fetchFeedList(countryCode: String, country: String): ResultModel<List<FeedEntity>>

    suspend fun setFeed(feedEntity: FeedEntity)

}
