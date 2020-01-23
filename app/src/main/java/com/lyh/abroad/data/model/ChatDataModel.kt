package com.lyh.abroad.data.model

data class ChatDataModel(
    val comments: Comments,
    val users: List<String>
)

data class Comments(
    val message: String,
    val timeStamp: Long,
    val uid: String
)