//package com.lyh.abroad.domain.interactor.chat
//
//import com.lyh.abroad.domain.entity.ChatEntity
//import com.lyh.abroad.domain.interactor.BaseUsecase
//import com.lyh.abroad.domain.model.ResultModel
//import com.lyh.abroad.domain.repository.ChatRepository
//import com.lyh.abroad.domain.repository.UserRepository
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class GetChatUsecase(
//    private val userRepository: UserRepository,
//    private val chatRepository: ChatRepository
//) : BaseUsecase<List<ChatEntity>, Nothing>() {
//
//    override suspend fun bindUsecase(param: Nothing?): ResultModel<List<ChatEntity>> =
//        withContext(Dispatchers.Main) {
//            val receiveChatResult = chatRepository.getChatList()
//            if (receiveChatResult.status == ResultModel.Status.SUCCESS) {
//                val data = receiveChatResult.data
//                if (data != null) {
//                    val users = receiveChatResult.data
//                        .flatMap { it.participant }
//                        .mapNotNull { userRepository.fetchUser(it).data }
//                    data.map {
//                        ChatEntity(
//                            it.message,
//                            it.timeStamp,
//                            users.first { user -> user.uid == it.uid },
//                            users
//                        )
//                    }.let {
//                        ResultModel.onSuccess(it)
//                    }
//                } else {
//                    ResultModel.onFailed()
//                }
//            } else {
//                ResultModel.onFailed()
//            }
//        }
//}
