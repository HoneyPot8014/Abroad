package com.lyh.abroad.domain.interactor.chatroom

import com.lyh.abroad.domain.entity.ChatRoomEntity
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.AuthRepository
import com.lyh.abroad.domain.repository.ChatRoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetChatRoomListUsecase(
    private val authRepository: AuthRepository,
    private val chatRoomRepository: ChatRoomRepository
) : BaseUsecase<List<ChatRoomEntity>, Nothing>() {

    override suspend fun bindUsecase(param: Nothing?): ResultModel<List<ChatRoomEntity>> =
        withContext(Dispatchers.Main) {
            val uid = authRepository.fetchId().data
                ?: return@withContext ResultModel.onFailed<List<ChatRoomEntity>>()
            chatRoomRepository.getChatRoomList(uid)
        }


}
