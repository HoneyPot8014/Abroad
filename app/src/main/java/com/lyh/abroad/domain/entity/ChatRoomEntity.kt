package com.lyh.abroad.domain.entity

data class ChatRoomEntity(
    val lastMessage: String = "",
    val masterProfileImageUrl: String = "",
    val memberInfo: Map<String, Boolean>,
    val title: String,
    val lastReadMessageTimestamp: Long = 0L,
    val pushToken: String,
    val userName: String,
    val uid: String,
    val postId: String
)
