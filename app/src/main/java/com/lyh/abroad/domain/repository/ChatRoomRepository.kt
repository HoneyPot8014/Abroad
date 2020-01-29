package com.lyh.abroad.domain.repository

import com.lyh.abroad.domain.entity.ChatRoomEntity
import com.lyh.abroad.domain.model.ResultModel

interface ChatRoomRepository {

    suspend fun getChatRoomList(): ResultModel<List<ChatRoomEntity>>

    suspend fun setChatRoom(uid: String): ResultModel<String>

    suspend fun addChatRoomUser(uid: String, chattingRoomId: String): ResultModel<Unit>

}
