package com.lyh.abroad.presenter.place

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.lyh.abroad.domain.interactor.place.GetCityUsecase
import com.lyh.abroad.domain.interactor.place.GetCountryUsecase
import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.mapper.CityMapper
import com.lyh.abroad.presenter.mapper.CountryMapper
import com.lyh.abroad.presenter.model.City
import com.lyh.abroad.presenter.model.Country

class PlaceViewModel(
    private val getCountryUsecase: GetCountryUsecase,
    private val getCityUsecase: GetCityUsecase
) : BaseViewModel() {

    val countrySearchTextLiveData = MutableLiveData<String>(null)
    val citySearchLiveData = MutableLiveData<String>(null)
    val countryLiveData = MutableLiveData<Country>()
    val cityLiveData = MutableLiveData<City>()
    val countryListLiveData = countrySearchTextLiveData.switchMap {
        liveData {
            emit(getCountryUsecase.execute(GetCountryUsecase.CountryParam(it)).data
                ?.map { CountryMapper.toModel(it) })
        }
    }
    val cityListLiveData = citySearchLiveData.switchMap {
        liveData {
            emit(getCityUsecase.execute(GetCityUsecase.CityParam(it)).data
                ?.map { CityMapper.toModel(it) })
        }
    }
    val isPlaceSelected = MediatorLiveData<Boolean>().apply {
        addSource(countryLiveData) {
            value = it != null && cityLiveData.value != null
        }
        addSource(cityLiveData) {
            value = it != null && countryLiveData.value != null
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

    fun onCityClicked(city: City?) {
        city?.let {
            if (cityLiveData.value != city) {
                cityLiveData.value = it
                _statusLiveData.value = Status.Success
            } else {
                cityLiveData.value = null
            }
        }
    }
}
