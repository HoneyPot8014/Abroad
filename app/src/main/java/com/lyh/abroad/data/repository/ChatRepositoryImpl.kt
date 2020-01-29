package com.lyh.abroad.data.repository

import com.lyh.abroad.data.source.chat.ChatDataSource
import com.lyh.abroad.domain.entity.ReceiveChatEntity
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.ChatRepository

class ChatRepositoryImpl(
    private val chatRemoteDataSource: ChatDataSource
): ChatRepository {

    companion object {

        private var INSTANCE: ChatRepositoryImpl? = null

        fun getInstance(chatRemoteDataSource: ChatDataSource) =
            INSTANCE ?: ChatRepositoryImpl(chatRemoteDataSource).also {
                INSTANCE = it
            }
    }

    override suspend fun setChatRoom(uid: String): ResultModel<String> =
        TODO()

    override suspend fun setChatRoom(uid: String, chattingRoomId: String): ResultModel<Unit> {
        TODO()
    }

    override suspend fun getChatList(): ResultModel<List<ReceiveChatEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
