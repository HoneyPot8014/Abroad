package com.lyh.abroad.domain.repository

import com.lyh.abroad.domain.entity.ReceiveChatEntity
import com.lyh.abroad.domain.model.ResultModel

interface ChatRepository {

    suspend fun setChatRoom(uid: String): ResultModel<String>

    suspend fun setChatRoom(uid: String, chattingRoomId: String): ResultModel<Unit>

    suspend fun getChatList(): ResultModel<List<ReceiveChatEntity>>

}
