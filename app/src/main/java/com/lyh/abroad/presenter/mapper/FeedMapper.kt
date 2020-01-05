package com.lyh.abroad.presenter.mapper

import com.lyh.abroad.domain.entity.FeedEntity
import com.lyh.abroad.presenter.model.Feed
import java.text.SimpleDateFormat
import java.util.*

object FeedMapper {

    fun toEntitiy(feed: Feed): FeedEntity {
        TODO()
    }

    fun toModel(feedEntity: FeedEntity): Feed {
        return Feed(
            feedEntity.chattingRoomId,
            feedEntity.contents,
            SimpleDateFormat("yyyy.MM.dd EEE", Locale.getDefault())
                .format(Date(feedEntity.createDate)),
            feedEntity.endDate,
            feedEntity.startDate,
            feedEntity.title,
            feedEntity.uid,
            feedEntity.userName
        )
    }
}