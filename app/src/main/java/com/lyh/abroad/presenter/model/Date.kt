package com.lyh.abroad.presenter.model

data class Date(
    val date: Long,
    val dateString: String,
    val displayDay: Int,
    val weeksOfMonth: Int,
    val daysOfWeek: Int
)