package com.lyh.abroad.domain.entity

data class ChatRoomEntity(
    val chatRoomId: String,
    val lastComment: String,
    val participantIdList: List<String>
)
