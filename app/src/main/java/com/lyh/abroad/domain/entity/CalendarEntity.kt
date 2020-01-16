package com.lyh.abroad.domain.entity

data class CalendarEntity(
    val year: Int,
    val month: Int,
    val days: List<DateEntity>
)