package com.lyh.abroad.data.source.chat

import com.lyh.abroad.domain.model.ResultModel

interface ChatDataSource {

    suspend fun setChatRoom(uid: String): ResultModel<String>

    suspend fun setChatRoom(uid: String, chattingRoomId: String): ResultModel<Unit>

}
