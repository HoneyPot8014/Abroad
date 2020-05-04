package com.lyh.abroad.domain.entity

data class PostEntity(
    val countryId: String,
    val cityId: String,
    val contents: String,
    val endDate: Long,
    val startDate: Long,
    val title: String,
    val uid: String,
    val userName: String,
    val postId: String
)