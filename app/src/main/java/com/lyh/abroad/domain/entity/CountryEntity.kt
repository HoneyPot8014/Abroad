package com.lyh.abroad.domain.entity

data class CountryEntity(
    val countryEmoji: String?,
    val countryCode: String,
    val countryName: String,
    val countryNameEn: String?,
    var queryMatchIndexSet: Set<Int>
)