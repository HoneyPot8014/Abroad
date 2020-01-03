package com.lyh.abroad.presenter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lyh.abroad.data.feed.repository.FeedRepositoryImpl
import com.lyh.abroad.data.feed.source.FeedRemoteSource
import com.lyh.abroad.domain.interactor.feed.GetFeedUsecase
import com.lyh.abroad.presenter.feed.FeedViewModel

object ViewModelFactory {

    fun get(): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                when {
                    modelClass.isAssignableFrom(FeedViewModel::class.java) -> {
                        modelClass
                            .getConstructor(GetFeedUsecase::class.java)
                            .newInstance(
                                GetFeedUsecase(FeedRepositoryImpl(FeedRemoteSource))
                            )
                    }
                    else -> throw Exception("can not create ViewModel : ${modelClass.name}")
                }
        }
}
