package com.lyh.abroad.domain.interactor.feed

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
        val endDate: Long,
        val startDate: Long,
        val title: String
    ) : Param()

    override suspend fun bindUsecase(param: SetFeedParam?): ResultModel<Unit> {
        return param?.let {
            withContext(Dispatchers.Main) {
                val uid = authRepository.fetchId().data
                    ?: return@withContext ResultModel.onFailed<Unit>()
                val user = userRepository.fetchUser(uid).data
                    ?: return@withContext ResultModel.onFailed<Unit>()
                val chattingRoomId = chatRoomRepository.setChatRoom(user.uid).data
                    ?: return@withContext ResultModel.onFailed<Unit>()
                feedRepository.setFeed(
                    PostEntity(
                        param.countryId,
                        param.cityId,
                        param.contents,
                        param.endDate,
                        param.startDate,
                        param.title,
                        uid,
                        user.userName,
                        chattingRoomId
                    )
                )
            }
        } ?: ResultModel.onFailed()
    }
}