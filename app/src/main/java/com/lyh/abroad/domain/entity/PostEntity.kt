package com.lyh.abroad.domain.entity

data class PostEntity(
    val countryId: String,
    val cityId: String,
    val contents: String,
    val endDate: String,
    val startDate: String,
    val title: String,
    val uid: String,
    val userName: String
)