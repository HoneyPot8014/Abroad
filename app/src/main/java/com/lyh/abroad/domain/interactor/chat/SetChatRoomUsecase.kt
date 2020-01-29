package com.lyh.abroad.domain.interactor.chat

import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.AuthRepository
import com.lyh.abroad.domain.repository.ChatRoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetChatRoomUsecase(
    private val authRepository: AuthRepository,
    private val chatRoomRepository: ChatRoomRepository
) : BaseUsecase<Unit, SetChatRoomUsecase.SetChatRoomParam>() {

    data class SetChatRoomParam(val chattingRoomId: String) : Param()

    override suspend fun bindUsecase(param: SetChatRoomParam?): ResultModel<Unit> =
        param?.chattingRoomId?.let { chattingRoomId ->
            withContext(Dispatchers.Main) {
                authRepository.fetchId().run {
                    data ?: return@withContext ResultModel.onFailed<Unit>(error)
                }.let {
                    chatRoomRepository.addChatRoomUser(it, chattingRoomId)
                }
            }
        } ?: ResultModel.onFailed()
}
