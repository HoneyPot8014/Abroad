package com.lyh.abroad.domain.entity

data class ReceiveChatEntity(
    val message: String,
    val timeStamp: Long,
    val uid: String,
    val participant: List<String>
)