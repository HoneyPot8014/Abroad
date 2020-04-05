package com.lyh.abroad.data.source.chatroom

import com.lyh.abroad.domain.entity.ChatRoomEntity
import com.lyh.abroad.domain.model.ResultModel

interface ChatRoomSource {

    suspend fun getChatRoomList(uid: String): ResultModel<List<ChatRoomEntity>>

    suspend fun setChatRoom(uid: String): ResultModel<String>

    suspend fun addChatRoomUser(uid: String, chattingRoomId: String): ResultModel<Unit>

}
