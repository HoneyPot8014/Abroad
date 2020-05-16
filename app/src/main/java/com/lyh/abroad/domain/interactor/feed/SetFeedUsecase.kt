package com.lyh.abroad.domain.interactor.feed

import com.lyh.abroad.domain.entity.ChatRoomEntity
import com.lyh.abroad.domain.entity.PostEntity
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.AuthRepository
import com.lyh.abroad.domain.repository.ChatRoomRepository
import com.lyh.abroad.domain.repository.FeedRepository
import com.lyh.abroad.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetFeedUsecase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val chatRoomRepository: ChatRoomRepository,
    private val feedRepository: FeedRepository
) : BaseUsecase<Unit, SetFeedUsecase.SetFeedParam>() {

    data class SetFeedParam(
        val countryId: String,
        val cityId: String,
        val contents: String,
        val endDate: String,
        val startDate: String,
        val title: String
    ) : Param()

    override suspend fun bindUsecase(param: SetFeedParam?): ResultModel<Unit> {
        return param?.let {
            withContext(Dispatchers.Main) {
                val uid = authRepository.fetchId().data
                    ?: return@withContext ResultModel.onFailed<Unit>()
                val userEntity = userRepository.fetchUser(uid).data
                    ?: return@withContext ResultModel.onFailed<Unit>()
                val setFeedResult = feedRepository.setFeed(
                    PostEntity(
                        param.countryId,
                        param.cityId,
                        param.contents,
                        param.endDate,
                        param.startDate,
                        param.title,
                        uid,
                        userEntity.userName
                    )
                )
                return@withContext if (setFeedResult.isSuccess) {
                    val chatRoomEntity = ChatRoomEntity(
                        memberInfo = mapOf(uid to true),
                        masterProfileImageUrl = userEntity.userImageUrl,
                        title = param.title,
                        pushToken = userEntity.pushToken,
                        userName = userEntity.userName,
                        uid = uid,
                        postId = setFeedResult.data ?: ""
                    )
                    chatRoomRepository.setChatRoom(chatRoomEntity)
                } else {
                    ResultModel.onFailed(setFeedResult.error)
                }
            }
        } ?: ResultModel.onFailed()
    }
}