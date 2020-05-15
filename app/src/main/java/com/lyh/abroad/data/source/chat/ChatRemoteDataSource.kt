package com.lyh.abroad.data.source.chat

import com.lyh.abroad.data.source.chatroom.ChatRoomRemoteSource.TABLE_CHAT_ROOMS
import com.lyh.abroad.data.source.firebase.FirebaseDb

object ChatRemoteDataSource : ChatDataSource {

    private val db = FirebaseDb.getDatabase(TABLE_CHAT_ROOMS)

}
