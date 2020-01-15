package com.lyh.abroad.presenter.mapper

import com.lyh.abroad.domain.entity.CityEntity
import com.lyh.abroad.presenter.model.City

object CityMapper {

    fun toModel(cityEntity: CityEntity): City =
        City(
            cityEntity.cityId,
            cityEntity.cityName,
            cityEntity.citySecondName,
            cityEntity.cityName
                .plus("  ")
                .plus("(")
                .plus(cityEntity.citySecondName)
                .plus(")")
        )
}