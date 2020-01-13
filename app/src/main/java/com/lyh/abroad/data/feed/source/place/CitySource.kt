package com.lyh.abroad.data.feed.source.place

import com.lyh.abroad.domain.entity.CityEntity
import com.lyh.abroad.domain.model.ResultModel

interface CitySource {

    suspend fun getCity(query: String): ResultModel<List<CityEntity>>

}