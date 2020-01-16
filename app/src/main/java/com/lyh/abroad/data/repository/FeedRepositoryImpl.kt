package com.lyh.abroad.data.repository

import androidx.annotation.UiThread
import com.lyh.abroad.data.mapper.FeedDataMapper
import com.lyh.abroad.data.mapper.PostDataMapper
import com.lyh.abroad.data.source.feed.FeedSource
import com.lyh.abroad.domain.entity.FeedEntity
import com.lyh.abroad.domain.entity.PostEntity
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.FeedRepository

class FeedRepositoryImpl private constructor(
    private val feedRemoteSource: FeedSource
) : FeedRepository {

    companion object {

        private var INSTANCE: FeedRepositoryImpl? = null

        @UiThread
        fun getInstance(feedRemoteSource: FeedSource): FeedRepositoryImpl =
            INSTANCE ?: kotlin.run {
                FeedRepositoryImpl(feedRemoteSource).also {
                    INSTANCE = it
                }
            }
    }

    override suspend fun fetchFeedList(countryCode: String): ResultModel<List<FeedEntity>> =
        feedRemoteSource.fetchFeedList(countryCode).mapNotNull {
            FeedDataMapper.toEntity(it)
        }.let {
            ResultModel.onSuccess(it)
        }

    override suspend fun setFeed(postEntity: PostEntity): ResultModel<Unit> {
        return feedRemoteSource.setFeed(
            postEntity.countryId,
            postEntity.cityId,
            PostDataMapper.toModel(postEntity)
        )
    }

}
