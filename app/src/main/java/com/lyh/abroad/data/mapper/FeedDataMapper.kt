package com.lyh.abroad.data.mapper

import com.lyh.abroad.data.model.FeedDataModel
import com.lyh.abroad.domain.entity.FeedEntity

object FeedDataMapper {

    fun toEntity(feedDataModel: FeedDataModel): FeedEntity? =
        if (feedDataModel.isEmpty()) {
            null
        } else {
            FeedEntity(
                feedDataModel.countryId!!,
                feedDataModel.cityId!!,
                feedDataModel.contents!!,
                feedDataModel.createDate!!,
                feedDataModel.endDate!!,
                feedDataModel.startDate!!,
                feedDataModel.title!!,
                feedDataModel.uid!!,
                feedDataModel.userName!!,
                feedDataModel.postId!!
            )
        }

    fun toModel(feedEntity: FeedEntity): FeedDataModel =
        FeedDataModel(
            feedEntity.countryId,
            feedEntity.cityId,
            feedEntity.contents,
            feedEntity.createDate,
            feedEntity.endDate,
            feedEntity.startDate,
            feedEntity.title,
            feedEntity.uid,
            feedEntity.userName,
            feedEntity.postId
        )
}
