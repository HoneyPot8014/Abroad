package com.lyh.abroad.domain.repository

import com.lyh.abroad.domain.entity.CityEntity
import com.lyh.abroad.domain.model.ResultModel

interface CityRepository {

    suspend fun getCity(query: String): ResultModel<List<CityEntity>>

}
