//package com.lyh.abroad.domain.interactor.chat
//
//import com.lyh.abroad.domain.interactor.BaseUsecase
//import com.lyh.abroad.domain.model.ResultModel
//import com.lyh.abroad.domain.model.ResultModel.Status.SUCCESS
//import com.lyh.abroad.domain.repository.AuthRepository
//import com.lyh.abroad.domain.repository.ChatRepository
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class CreateChatRoomUsecase(
//    private val authRepository: AuthRepository,
//    private val chatRepository: ChatRepository
//) : BaseUsecase<Unit, Nothing>() {
//
//    override suspend fun bindUsecase(param: Nothing?): ResultModel<Unit> =
//        withContext(Dispatchers.Main) {
//            authRepository.fetchId().run {
//                data ?: return@withContext ResultModel.onFailed<Unit>(error)
//            }.let {
//                chatRepository.setChatRoom(it).let { result ->
//                    if (result.status == SUCCESS) {
//                        ResultModel.onSuccess(Unit)
//                    } else {
//                        ResultModel.onFailed(result.error)
//                    }
//                }
//            }
//        }
//}
