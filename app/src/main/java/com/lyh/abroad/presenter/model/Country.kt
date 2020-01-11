package com.lyh.abroad.presenter.model

import android.text.SpannedString

data class Country(
    val countryCode: String,
    val countryName: String,
    val displayCountry: SpannedString
)