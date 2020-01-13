package com.lyh.abroad.data.feed.repository

import com.lyh.abroad.data.feed.source.place.CitySource
import com.lyh.abroad.domain.entity.CityEntity
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.CityRepository

class CityRepositoryImpl private constructor(
    private val citySource: CitySource
) : CityRepository {

    companion object {

        private var INSTANCE: CityRepositoryImpl? = null

        fun getInstance(citySource: CitySource) =
            INSTANCE ?: kotlin.run {
                CityRepositoryImpl(citySource).also {
                    INSTANCE = it
                }
            }
    }

    override suspend fun getCity(query: String): ResultModel<List<CityEntity>> {
        return citySource.getCity(query)
    }
}