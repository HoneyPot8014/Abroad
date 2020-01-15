package com.lyh.abroad.domain.entity

data class PostEntity(
    val countryId: String,
    val cityId: String,
    val chattingRoomId: String,
    val contents: String,
    val createDate: String,
    val endDate: String,
    val startDate: String,
    val title: String,
    val uid: String,
    val userName: String
)