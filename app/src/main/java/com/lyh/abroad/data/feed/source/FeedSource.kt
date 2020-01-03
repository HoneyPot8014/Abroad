package com.lyh.abroad.data.feed.source

import com.lyh.abroad.data.feed.model.FeedDataModel
import com.lyh.abroad.domain.entity.FeedEntity

interface FeedSource {

    suspend fun fetchFeedList(countryCode: String, country: String): List<FeedDataModel>

    suspend fun setFeed(feedEntity: FeedEntity)
}