package com.lyh.abroad.presenter.post

import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.R
import com.lyh.abroad.domain.interactor.feed.SetFeedUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.base.BaseViewModel.Status.Failed
import com.lyh.abroad.presenter.base.BaseViewModel.Status.Success
import com.lyh.abroad.presenter.model.City
import com.lyh.abroad.presenter.model.Country
import com.lyh.abroad.presenter.model.Date
import com.lyh.abroad.presenter.post.PostViewModel.PostFailReason.*
import kotlinx.coroutines.launch

class PostViewModel(
    private val setFeedUsecase: SetFeedUsecase
) : BaseViewModel() {

    val titleLiveData = MutableLiveData<String>()
    val contentLiveData = MutableLiveData<String>()

    fun checkFeedContent(country: Country?, city: City?) {
        when {
            titleLiveData.value.isNullOrEmpty() || titleLiveData.value.isNullOrBlank() ->
                _statusLiveData.value = Failed(EmptyTitle)
            contentLiveData.value.isNullOrEmpty() || contentLiveData.value.isNullOrBlank() ->
                _statusLiveData.value = Failed(EmptyContent)
            country == null || city == null -> _statusLiveData.value = Failed(EmptyPlace)
            else -> _statusLiveData.value = Success
        }
    }

    fun setFeed(country: Country?, city: City?, period: List<Date>?) {
        when {
            titleLiveData.value.isNullOrEmpty() || titleLiveData.value.isNullOrBlank() ->
                _statusLiveData.value = Failed(EmptyTitle)
            contentLiveData.value.isNullOrEmpty() || contentLiveData.value.isNullOrBlank() ->
                _statusLiveData.value = Failed(EmptyContent)
            country == null || city == null -> _statusLiveData.value = Failed(EmptyPlace)
            period.isNullOrEmpty() -> _statusLiveData.value = Failed(NoPeriod)
            else -> {
                viewModelScope.launch {
                    setFeedUsecase.execute(
                        SetFeedUsecase.SetFeedParam(
                            country.countryCode,
                            city.cityId,
                            contentLiveData.value ?: "",
                            period.first().dateString,
                            period.last().dateString,
                            titleLiveData.value ?: ""
                        )
                    ).also {
                        if (it.status == ResultModel.Status.SUCCESS) {
                            _statusLiveData.value = Success
                        } else {
                            _statusLiveData.value = Failed(PostFailed)
                        }
                    }
                }
            }
        }
    }

    sealed class PostFailReason(message: Int) : Reason(message) {
        object EmptyPlace : PostFailReason(R.string.not_selected_place)
        object NoPeriod : PostFailReason(R.string.no_period)
        object EmptyTitle : PostFailReason(R.string.empty_title)
        object EmptyContent : PostFailReason(R.string.empty_content)
        object PostFailed: PostFailReason(R.string.post_failed)
    }

}