package com.lyh.abroad.domain.repository

import com.lyh.abroad.domain.entity.FeedEntity

interface FeedRepository {

    suspend fun fetchFeedList(countryCode: String, country: String): List<FeedEntity>

    suspend fun setFeed(feedEntity: FeedEntity)

}
