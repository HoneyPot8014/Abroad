package com.lyh.abroad.data.mapper

import com.lyh.abroad.data.model.FeedDataModel
import com.lyh.abroad.domain.entity.FeedEntity

object FeedDataMapper {

    fun toEntity(feedDataModel: FeedDataModel): FeedEntity? {
        return if (feedDataModel.isEmpty()) {
            null
        } else {
            FeedEntity(
                feedDataModel.countryId!!,
                feedDataModel.cityId!!,
                feedDataModel.chattingRoomId!!,
                feedDataModel.contents!!,
                feedDataModel.createDate!!,
                feedDataModel.endDate!!,
                feedDataModel.startDate!!,
                feedDataModel.title!!,
                feedDataModel.uid!!,
                feedDataModel.userName!!
            )
        }
    }

    fun toModel(feedEntity: FeedEntity): FeedDataModel =
        FeedDataModel(
            feedEntity.countryId,
            feedEntity.cityId,
            feedEntity.chattingRoomId,
            feedEntity.contents,
            feedEntity.createDate,
            feedEntity.endDate,
            feedEntity.startDate,
            feedEntity.title,
            feedEntity.uid,
            feedEntity.userName
        )
}
