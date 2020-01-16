package com.lyh.abroad.data.source.feed

import com.lyh.abroad.data.model.FeedDataModel
import com.lyh.abroad.data.model.PostDataModel
import com.lyh.abroad.domain.model.ResultModel

interface FeedSource {

    suspend fun fetchFeedList(countryCode: String): List<FeedDataModel>

    suspend fun setFeed(countryId: String, cityId: String, postDataModel: PostDataModel): ResultModel<Unit>
}