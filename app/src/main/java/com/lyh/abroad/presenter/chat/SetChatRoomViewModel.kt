package com.lyh.abroad.presenter.chat

import com.lyh.abroad.domain.interactor.chatroom.SetChatRoomUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.presenter.base.BaseViewModel
import kotlinx.coroutines.launch

class SetChatRoomViewModel(
    private val setChatRoomUsecase: SetChatRoomUsecase
) : BaseViewModel() {

    fun createChat(chattingRoomId: String) {
        viewModelScope.launch {
            setChatRoomUsecase.execute(
                SetChatRoomUsecase.SetChatRoomParam(chattingRoomId)
            ).run {
                if (status == ResultModel.Status.SUCCESS) {
                    _statusLiveData.value = Status.Success
                }
            }
        }
    }

}
