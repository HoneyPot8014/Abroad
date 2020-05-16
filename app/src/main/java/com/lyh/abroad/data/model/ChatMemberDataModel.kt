package com.lyh.abroad.data.model

data class ChatMemberInfoModel(
    val infos: ChatUserDataModel?,
    val memberInfo: Map<String, Boolean>?
)

data class ChatUserDataModel(
    val lastReadMessageTimestamp: Long? = 0L,
    val profileImageUrl: String?,
    val pushToken: String?,
    val userName: String?
)
