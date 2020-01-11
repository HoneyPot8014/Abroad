package com.lyh.abroad.presenter.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.lyh.abroad.domain.interactor.place.GetCountryUsecase
import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.mapper.CountryMapper
import com.lyh.abroad.presenter.model.Country

class PlaceViewModel(
    private val getCountryUsecase: GetCountryUsecase
) : BaseViewModel() {

    val searchTextLiveData = MutableLiveData<String>(null)
    val countryLiveData = MutableLiveData<Country>()
    val countryListLiveData = searchTextLiveData.switchMap {
        liveData {
            emit(
                getCountryUsecase.execute(GetCountryUsecase.CountryParam(it)).data
                    ?.map { CountryMapper.toModel(it) }
            )
        }
    }

    fun onCountryClicked(country: Country?) {
        country?.let {
            if (countryLiveData.value != country) {
                countryLiveData.value = it
            } else {
                countryLiveData.value = null
            }
        }
    }
}
