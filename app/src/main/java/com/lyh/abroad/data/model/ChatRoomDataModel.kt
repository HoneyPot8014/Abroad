package com.lyh.abroad.data.model

import com.google.firebase.database.ServerValue

data class ChatRoomDataModel(
    val lastMessage: String? = "",
    val masterProfileImageUrl: String?,
    val memberInfo: Map<String, Boolean>?,
    val timeStamp: Map<String, String> = ServerValue.TIMESTAMP,
    val title: String?
)

data class Comments(
    val message: String,
    val timeStamp: Long,
    val uid: String
)