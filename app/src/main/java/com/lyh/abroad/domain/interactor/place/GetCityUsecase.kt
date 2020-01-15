package com.lyh.abroad.domain.interactor.place

import com.lyh.abroad.domain.entity.CityEntity
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCityUsecase(
    private val cityRepository: CityRepository
) : BaseUsecase<List<CityEntity>, GetCityUsecase.CityParam>() {

    data class CityParam(val query: String?) : Param()

    override suspend fun bindUsecase(param: CityParam?): ResultModel<List<CityEntity>> =
        param?.query?.let {
            withContext(Dispatchers.Main) {
                cityRepository.getCity(it)
            }
        } ?: ResultModel.onSuccess(emptyList())
}
