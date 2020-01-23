package com.lyh.abroad.domain.entity

data class ChatEntity(
    val message: String,
    val timeStamp: Long,
    val sendUser: UserEntity,
    val participant: List<UserEntity>
)