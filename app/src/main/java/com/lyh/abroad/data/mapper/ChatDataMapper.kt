package com.lyh.abroad.data.mapper

import com.lyh.abroad.data.model.ChatRoomDataModel
import com.lyh.abroad.domain.entity.ChatRoomEntity

object ChatDataMapper {

    fun toModel(chatRoomEntity: ChatRoomEntity) =
        ChatRoomDataModel(
            masterProfileImageUrl = chatRoomEntity.masterProfileImageUrl,
            memberInfo = mapOf(chatRoomEntity.uid to true),
            title = chatRoomEntity.title
        )
}