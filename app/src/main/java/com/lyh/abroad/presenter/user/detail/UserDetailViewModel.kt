package com.lyh.abroad.presenter.user.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.interactor.user.GetUserUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.model.Feed
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val getUserUsecase: GetUserUsecase
) : BaseViewModel() {

    private val _userLiveData = MutableLiveData<UserEntity>()
    val userLiveData: LiveData<UserEntity>
        get() = _userLiveData

    fun setFeed(feed: Feed) {
        viewModelScope.launch {
            getUserUsecase.execute(GetUserUsecase.GetUserParam(feed.id)).run {
                if (status == ResultModel.Status.SUCCESS) {
                    _userLiveData.value = data
                }
            }
        }
    }

}