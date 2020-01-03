package com.lyh.abroad.presenter.feed

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.domain.entity.FeedEntity
import com.lyh.abroad.domain.interactor.feed.GetFeedUsecase
import com.lyh.abroad.presenter.base.BaseViewModel
import kotlinx.coroutines.launch

class FeedViewModel(
    private val getFeedUsecase: GetFeedUsecase
): BaseViewModel() {

    private val _feedListLiveData = MutableLiveData<List<FeedEntity>>()
    val feedListLiveData
        get() = _feedListLiveData

    init {
        viewModelScope.launch {
            val result = getFeedUsecase.execute(GetFeedUsecase.FeedParam("KR", "temp"))
            _feedListLiveData.value = result
            Log.d("용현", "${feedListLiveData.value}")
        }
    }

}