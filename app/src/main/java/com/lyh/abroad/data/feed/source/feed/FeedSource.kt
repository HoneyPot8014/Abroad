package com.lyh.abroad.data.feed.source.feed

import com.lyh.abroad.data.feed.model.FeedDataModel
import com.lyh.abroad.data.feed.model.PostDataModel
import com.lyh.abroad.domain.model.ResultModel

interface FeedSource {

    suspend fun fetchFeedList(countryCode: String): List<FeedDataModel>

    suspend fun setFeed(countryId: String, cityId: String, postDataModel: PostDataModel): ResultModel<Unit>
}