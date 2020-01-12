package com.lyh.abroad.presenter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lyh.abroad.data.feed.repository.FeedRepositoryImpl
import com.lyh.abroad.data.feed.repository.UserRepositoryImpl
import com.lyh.abroad.data.feed.source.feed.FeedRemoteSource
import com.lyh.abroad.data.feed.source.user.UserAuth
import com.lyh.abroad.data.feed.source.user.UserRemoteSource
import com.lyh.abroad.domain.interactor.feed.GetFeedUsecase
import com.lyh.abroad.domain.interactor.place.GetCountryUsecase
import com.lyh.abroad.domain.interactor.user.SignInUsecase
import com.lyh.abroad.domain.interactor.user.SignUpUsecase
import com.lyh.abroad.presenter.feed.FeedViewModel
import com.lyh.abroad.presenter.place.PlaceViewModel
import com.lyh.abroad.presenter.user.SignInViewModel
import com.lyh.abroad.presenter.user.SignUpViewModel

object ViewModelFactory {

    fun get(): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                when {
                    modelClass.isAssignableFrom(FeedViewModel::class.java) -> {
                        modelClass.getConstructor(GetFeedUsecase::class.java)
                            .newInstance(GetFeedUsecase(FeedRepositoryImpl.getInstance(FeedRemoteSource)))
                    }
                    modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                        modelClass.getConstructor(SignInUsecase::class.java)
                            .newInstance(SignInUsecase(UserRepositoryImpl.getInstance(UserAuth, UserRemoteSource)))
                    }
                    modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                        modelClass.getConstructor(SignUpUsecase::class.java)
                            .newInstance(SignUpUsecase(UserRepositoryImpl.getInstance(UserAuth, UserRemoteSource)))
                    }
                    modelClass.isAssignableFrom(PlaceViewModel::class.java) -> {
                        modelClass.getConstructor(GetCountryUsecase::class.java)
                            .newInstance(GetCountryUsecase())
                    }
                    else -> throw Exception("can not create ViewModel : ${modelClass.name}")
                }
        }
}
