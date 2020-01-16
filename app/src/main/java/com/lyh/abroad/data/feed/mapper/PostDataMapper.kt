package com.lyh.abroad.data.feed.mapper

import com.lyh.abroad.data.feed.model.PostDataModel
import com.lyh.abroad.domain.entity.PostEntity

object PostDataMapper {

    fun toModel(postEntity: PostEntity): PostDataModel =
        PostDataModel(
            postEntity.contents,
            postEntity.endDate,
            postEntity.startDate,
            postEntity.title,
            postEntity.uid,
            postEntity.userName
        )
}