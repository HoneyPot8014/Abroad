package com.lyh.abroad.domain.repository

import com.lyh.abroad.domain.entity.FeedEntity

interface FeedRepository {

    suspend fun fetchFeed(): FeedEntity

    suspend fun setFeed(feedEntity: FeedEntity)

}
