package com.lyh.abroad.presenter.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.lyh.abroad.domain.entity.CityEntity
import com.lyh.abroad.domain.interactor.place.GetCityUsecase
import com.lyh.abroad.domain.interactor.place.GetCountryUsecase
import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.mapper.CountryMapper
import com.lyh.abroad.presenter.model.Country

class PlaceViewModel(
    private val getCountryUsecase: GetCountryUsecase,
    private val getCityUsecase: GetCityUsecase
) : BaseViewModel() {

    val countrySearchTextLiveData = MutableLiveData<String>(null)
    val citySearchLiveData = MutableLiveData<String>(null)
    val countryLiveData = MutableLiveData<Country>()
    val cityLiveData = MutableLiveData<CityEntity>()
    val countryListLiveData = countrySearchTextLiveData.switchMap {
        liveData {
            emit(getCountryUsecase.execute(GetCountryUsecase.CountryParam(it)).data
                ?.map { CountryMapper.toModel(it) })
        }
    }

    val cityListLiveData = citySearchLiveData.switchMap {
        liveData {
            emit(getCityUsecase.execute(GetCityUsecase.CityParam(it)).data)
        }
    }


    fun onCountryClicked(country: Country?) {
        country?.let {
            if (countryLiveData.value != country) {
                countryLiveData.value = it
                _statusLiveData.value = Status.Success
            } else {
                countryLiveData.value = null
            }
        }
    }

    fun onCityClicked(cityEntity: CityEntity?) {
        cityEntity?.let {
            if (cityLiveData.value != cityEntity) {
                cityLiveData.value = it
                _statusLiveData.value = Status.Success
            } else {
                cityLiveData.value = null
            }
        }
    }
}
