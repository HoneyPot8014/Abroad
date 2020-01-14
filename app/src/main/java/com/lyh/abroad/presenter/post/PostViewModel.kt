package com.lyh.abroad.presenter.post

import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.presenter.base.BaseViewModel

class PostViewModel (

): BaseViewModel() {

    val titleLiveData = MutableLiveData<String>()
    val postLiveData = MutableLiveData<String>()

}