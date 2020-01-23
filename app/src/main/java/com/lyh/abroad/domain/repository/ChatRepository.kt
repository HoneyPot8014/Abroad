package com.lyh.abroad.domain.repository

import com.lyh.abroad.domain.entity.ReceiveChatEntity
import com.lyh.abroad.domain.model.ResultModel

interface ChatRepository {

    suspend fun getChatList(): ResultModel<List<ReceiveChatEntity>>

}