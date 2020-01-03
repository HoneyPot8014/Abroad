package com.lyh.abroad.data.feed.repository

import androidx.annotation.UiThread
import com.lyh.abroad.data.feed.mapper.FeedEntityMapper
import com.lyh.abroad.data.feed.source.FeedSource
import com.lyh.abroad.domain.entity.FeedEntity
import com.lyh.abroad.domain.repository.FeedRepository

class FeedRepositoryImpl(
    private val feedRemoteSource: FeedSource
) : FeedRepository {

    companion object {

        private var INSTANCE: FeedRepositoryImpl? = null

        @UiThread
        fun getInstance(feedRemoteSource: FeedSource): FeedRepositoryImpl {
            return INSTANCE ?: kotlin.run {
                FeedRepositoryImpl(feedRemoteSource).also {
                    INSTANCE = it
                }
            }
        }

    }

    override suspend fun fetchFeedList(countryCode: String, country: String): List<FeedEntity> =
        feedRemoteSource.fetchFeedList(countryCode, country).mapNotNull {
            FeedEntityMapper.toEntity(it)
        }

    override suspend fun setFeed(feedEntity: FeedEntity) {

    }

}
