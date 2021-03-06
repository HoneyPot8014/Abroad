package com.lyh.abroad.data.model

import com.google.firebase.database.ServerValue

data class PostDataModel(
    val countryId: String,
    val cityId: String,
    val contents: String,
    val endDate: String,
    val startDate: String,
    val title: String,
    val uid: String,
    val userName: String,
    val postId: String = "",
    val createDate: Map<String, String> = ServerValue.TIMESTAMP
)