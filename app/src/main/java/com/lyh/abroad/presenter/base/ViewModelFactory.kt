package com.lyh.abroad.presenter.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lyh.abroad.data.repository.*
import com.lyh.abroad.data.source.auth.AuthRemoteSource
import com.lyh.abroad.data.source.calendar.CalendarLocalSource
import com.lyh.abroad.data.source.chat.ChatRemoteDataSource
import com.lyh.abroad.data.source.feed.FeedRemoteSource
import com.lyh.abroad.data.source.place.CityRemoteSource
import com.lyh.abroad.data.source.user.UserRemoteSource
import com.lyh.abroad.domain.interactor.chat.SetChatRoomUsecase
import com.lyh.abroad.domain.interactor.date.GetCalendarUsecase
import com.lyh.abroad.domain.interactor.feed.GetFeedUsecase
import com.lyh.abroad.domain.interactor.feed.SetFeedUsecase
import com.lyh.abroad.domain.interactor.place.GetCityUsecase
import com.lyh.abroad.domain.interactor.place.GetCountryUsecase
import com.lyh.abroad.domain.interactor.user.GetUserUsecase
import com.lyh.abroad.domain.interactor.user.SignInUsecase
import com.lyh.abroad.domain.interactor.user.SignUpUsecase
import com.lyh.abroad.presenter.calendar.CalendarViewModel
import com.lyh.abroad.presenter.chat.SetChatRoomViewModel
import com.lyh.abroad.presenter.feed.list.FeedListViewModel
import com.lyh.abroad.presenter.place.PlaceViewModel
import com.lyh.abroad.presenter.post.PostViewModel
import com.lyh.abroad.presenter.signin.SignInViewModel
import com.lyh.abroad.presenter.signin.SignUpViewModel
import com.lyh.abroad.presenter.user.UserViewModel
import com.lyh.abroad.presenter.user.detail.UserDetailViewModel

object ViewModelFactory {

    fun get(context: Context): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                when {
                    modelClass.isAssignableFrom(FeedListViewModel::class.java) -> {
                        modelClass.getConstructor(GetFeedUsecase::class.java)
                            .newInstance(GetFeedUsecase(FeedRepositoryImpl.getInstance(FeedRemoteSource)))
                    }
                    modelClass.isAssignableFrom(UserDetailViewModel::class.java) -> {
                        modelClass.getConstructor(GetUserUsecase::class.java)
                            .newInstance(GetUserUsecase(
                                AuthRepositoryImpl.getInstance(AuthRemoteSource),
                                UserRepositoryImpl.getInstance(UserRemoteSource)
                            ))
                    }
                    modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                        modelClass.getConstructor(SignInUsecase::class.java)
                            .newInstance(SignInUsecase(
                                AuthRepositoryImpl.getInstance(AuthRemoteSource),
                                UserRepositoryImpl.getInstance(UserRemoteSource)
                            ))
                    }
                    modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                        modelClass.getConstructor(SignUpUsecase::class.java)
                            .newInstance(SignUpUsecase(
                                AuthRepositoryImpl.getInstance(AuthRemoteSource),
                                UserRepositoryImpl.getInstance(UserRemoteSource)
                            ))
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
                            .newInstance(GetUserUsecase(
                                AuthRepositoryImpl.getInstance(AuthRemoteSource),
                                UserRepositoryImpl.getInstance(UserRemoteSource)
                            ))
                    }
                    modelClass.isAssignableFrom(CalendarViewModel::class.java) -> {
                        modelClass.getConstructor(GetCalendarUsecase::class.java)
                            .newInstance(GetCalendarUsecase(CalendarRepositoryImpl.getInstance(CalendarLocalSource)))
                    }
                    modelClass.isAssignableFrom(PostViewModel::class.java) -> {
                        modelClass.getConstructor(SetFeedUsecase::class.java)
                            .newInstance(SetFeedUsecase(
                                AuthRepositoryImpl.getInstance(AuthRemoteSource),
                                UserRepositoryImpl.getInstance(UserRemoteSource),
                                ChatRepositoryImpl.getInstance(ChatRemoteDataSource),
                                FeedRepositoryImpl.getInstance(FeedRemoteSource))
                            )
                    }
                    modelClass.isAssignableFrom(SetChatRoomViewModel::class.java) -> {
                        modelClass.getConstructor(SetChatRoomUsecase::class.java)
                            .newInstance(SetChatRoomUsecase(
                                AuthRepositoryImpl.getInstance(AuthRemoteSource),
                                    ChatRepositoryImpl.getInstance(ChatRemoteDataSource)
                            ))
                    }
                    else -> throw Exception("can not create ViewModel : ${modelClass.name}")
                }
        }
}
