package com.lyh.abroad.presenter.feed

import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.domain.interactor.feed.GetFeedUsecase
import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.mapper.FeedMapper
import com.lyh.abroad.presenter.model.Feed
import kotlinx.coroutines.launch

class FeedViewModel(
    private val getFeedUsecase: GetFeedUsecase
): BaseViewModel() {

    private val _feedListLiveData = MutableLiveData<List<Feed>>()
    val feedListLiveData
        get() = _feedListLiveData

    init {
        viewModelScope.launch {
            val result = getFeedUsecase.execute(GetFeedUsecase.FeedParam("KR", "temp"))
                .map { FeedMapper.toModel(it) }
            _feedListLiveData.value = result
        }
    }
}
