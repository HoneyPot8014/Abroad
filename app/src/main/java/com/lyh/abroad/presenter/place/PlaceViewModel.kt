package com.lyh.abroad.presenter.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.lyh.abroad.domain.interactor.place.GetCountryUsecase
import com.lyh.abroad.presenter.base.BaseViewModel

class PlaceViewModel(
    private val getCountryUsecase: GetCountryUsecase
): BaseViewModel() {

    val searchTextLiveData = MutableLiveData<String>(null)
    val countryLiveData = searchTextLiveData.switchMap {
        liveData {
            emit(getCountryUsecase.execute(GetCountryUsecase.CountryParam(it)).data)
        }
    }
}
