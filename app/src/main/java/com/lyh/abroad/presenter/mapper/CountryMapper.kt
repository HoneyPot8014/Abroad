package com.lyh.abroad.presenter.mapper

import androidx.core.text.buildSpannedString
import androidx.core.text.color
import com.lyh.abroad.domain.entity.CountryEntity
import com.lyh.abroad.presenter.model.Country

object CountryMapper {

    fun toEntity(country: Country): CountryEntity {
        TODO()
    }

    fun toModel(countryEntity: CountryEntity): Country {
        val coloredCountryName = buildSpannedString {
            append(countryEntity.countryEmoji)
            append("    ")
            countryEntity.countryName.forEachIndexed { index, c ->
                if (countryEntity.queryMatchIndexSet.contains(index)) {
                    color(0xFF3b735f.toInt()) {
                        append(c)
                    }
                } else {
                    append(c)
                }
            }
            if (!countryEntity.countryNameEn.isNullOrEmpty()) {
                append(" - ")
                append(countryEntity.countryNameEn)
            }
        }
        return Country(
            countryEntity.countryCode,
            countryEntity.countryName,
            coloredCountryName
        )
    }
}