package com.lyh.abroad.domain.interactor.chat

import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.model.ResultModel.Status.SUCCESS
import com.lyh.abroad.domain.repository.ChatRepository
import com.lyh.abroad.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SetChatRoomUsecase(
    private val userRepository: UserRepository,
    private val chatRepository: ChatRepository
) : BaseUsecase<Unit, Nothing>() {

    override suspend fun bindUsecase(param: Nothing?): ResultModel<Unit> =
        withContext(Dispatchers.Main) {
            userRepository.getUid().run {
                data ?: return@withContext ResultModel.onFailed<Unit>(error)
            }.let {
                chatRepository.setChatRoom(it).let { result ->
                    if (result.status == SUCCESS) {
                        ResultModel.onSuccess(Unit)
                    } else {
                        ResultModel.onFailed(result.error)
                    }
                }
            }
        }
}
