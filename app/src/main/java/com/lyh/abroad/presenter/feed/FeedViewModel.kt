package com.lyh.abroad.presenter.feed

import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.R
import com.lyh.abroad.domain.interactor.feed.GetFeedUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.mapper.FeedMapper
import com.lyh.abroad.presenter.model.Feed
import kotlinx.coroutines.launch

class FeedViewModel(
    private val getFeedUsecase: GetFeedUsecase
) : BaseViewModel() {

    private val _feedListLiveData = MutableLiveData<List<Feed>>()
    val feedListLiveData
        get() = _feedListLiveData

    init {
        viewModelScope.launch {
            getFeedUsecase.execute(GetFeedUsecase.FeedParam("KR")).run {
                if (status == ResultModel.Status.SUCCESS) {
                    data?.map { FeedMapper.toModel(it) }
                        .also {
                            _feedListLiveData.value = it
                        }
                } else {
                    _statusLiveData.value = Status.Failed(FailReason.NetworkFailed)
                }
            }
        }
    }

    sealed class FailReason(message: Int): Reason(message) {
        object NetworkFailed : FailReason(R.string.no_auth)
    }
}
