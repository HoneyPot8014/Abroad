package com.lyh.abroad.data.repository

import com.lyh.abroad.data.source.chatroom.ChatRoomSource
import com.lyh.abroad.domain.entity.ChatRoomEntity
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.ChatRoomRepository

class ChatRoomRepositoryImpl(
    private val chatRoomSource: ChatRoomSource
) : ChatRoomRepository {

    companion object {

        private var INSTANCE: ChatRoomRepositoryImpl? = null

        fun getInstance(chatRoomSource: ChatRoomSource) =
            INSTANCE ?: ChatRoomRepositoryImpl(chatRoomSource).also {
                INSTANCE = it
            }
    }

    override suspend fun getChatRoomList(): ResultModel<List<ChatRoomEntity>> =
        chatRoomSource.getChatRoomList()

    override suspend fun setChatRoom(uid: String): ResultModel<String> =
        chatRoomSource.setChatRoom(uid)

    override suspend fun addChatRoomUser(uid: String, chattingRoomId: String): ResultModel<Unit> =
        chatRoomSource.addChatRoomUser(uid, chattingRoomId)
}
