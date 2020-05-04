package com.lyh.abroad.data.model

import com.google.firebase.database.ServerValue

data class PostDataModel(
    /** 국가 id*/
    val countryId: String,
    /** 도시 id*/
    val cityId: String,
    /** 피드 내용*/
    val contents: String,
    val endDate: Long,
    val startDate: Long,
    val title: String,
    val uid: String,
    val userName: String,
    val postId: String,
    val createDate: Map<String, String> = ServerValue.TIMESTAMP
)