package com.lyh.abroad.data.mapper

import com.lyh.abroad.data.model.PostDataModel
import com.lyh.abroad.domain.entity.PostEntity

object PostDataMapper {

    fun toModel(postEntity: PostEntity): PostDataModel =
        PostDataModel(
            postEntity.countryId,
            postEntity.cityId,
            postEntity.contents,
            postEntity.endDate,
            postEntity.startDate,
            postEntity.title,
            postEntity.uid,
            postEntity.userName,
            postEntity.postId
        )
}
