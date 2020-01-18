package com.lyh.abroad.presenter.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lyh.abroad.data.repository.CalendarRepositoryImpl
import com.lyh.abroad.data.repository.CityRepositoryImpl
import com.lyh.abroad.data.repository.FeedRepositoryImpl
import com.lyh.abroad.data.repository.UserRepositoryImpl
import com.lyh.abroad.data.source.calendar.CalendarLocalSource
import com.lyh.abroad.data.source.feed.FeedRemoteSource
import com.lyh.abroad.data.source.place.CityRemoteSource
import com.lyh.abroad.data.source.user.UserAuth
import com.lyh.abroad.data.source.user.UserRemoteSource
import com.lyh.abroad.domain.interactor.date.GetCalendarUsecase
import com.lyh.abroad.domain.interactor.feed.GetFeedUsecase
import com.lyh.abroad.domain.interactor.place.GetCityUsecase
import com.lyh.abroad.domain.interactor.place.GetCountryUsecase
import com.lyh.abroad.domain.interactor.user.GetUserUsecase
import com.lyh.abroad.domain.interactor.user.SignInUsecase
import com.lyh.abroad.domain.interactor.user.SignUpUsecase
import com.lyh.abroad.presenter.base.signin.SignInViewModel
import com.lyh.abroad.presenter.base.signin.SignUpViewModel
import com.lyh.abroad.presenter.calendar.CalendarViewModel
import com.lyh.abroad.presenter.feed.FeedViewModel
import com.lyh.abroad.presenter.place.PlaceViewModel
import com.lyh.abroad.presenter.user.UserViewModel

object ViewModelFactory {

    fun get(context: Context): ViewModelProvider.Factory =
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
                        modelClass.getConstructor(GetCountryUsecase::class.java, GetCityUsecase::class.java)
                            .newInstance(
                                GetCountryUsecase(),
                                GetCityUsecase(CityRepositoryImpl.getInstance(CityRemoteSource.getInstance(context)))
                            )
                    }
                    modelClass.isAssignableFrom(UserViewModel::class.java) -> {
                        modelClass.getConstructor(GetUserUsecase::class.java)
                            .newInstance(GetUserUsecase(UserRepositoryImpl.getInstance(UserAuth, UserRemoteSource)))
                    }
                    modelClass.isAssignableFrom(CalendarViewModel::class.java) -> {
                        modelClass.getConstructor(GetCalendarUsecase::class.java)
                            .newInstance(GetCalendarUsecase(CalendarRepositoryImpl.getInstance(CalendarLocalSource)))
                    }
                    else -> throw Exception("can not create ViewModel : ${modelClass.name}")
                }
        }
}
