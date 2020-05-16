package com.lyh.abroad.data.mapper

import com.lyh.abroad.data.model.ChatMemberInfoModel
import com.lyh.abroad.data.model.ChatUserDataModel
import com.lyh.abroad.domain.entity.ChatRoomEntity

object ChatRoomMemberDataMapper {

    fun toModel(chatRoomEntity: ChatRoomEntity): ChatMemberInfoModel {
        return ChatMemberInfoModel(
            ChatUserDataModel(
                profileImageUrl = chatRoomEntity.masterProfileImageUrl,
                pushToken = chatRoomEntity.pushToken,
                userName = chatRoomEntity.userName
            ),
            mapOf(chatRoomEntity.uid to true)
        )
    }
}