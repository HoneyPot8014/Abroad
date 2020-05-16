package com.lyh.abroad.data.source.chatroom

import com.lyh.abroad.data.mapper.ChatDataMapper
import com.lyh.abroad.data.mapper.ChatRoomMemberDataMapper
import com.lyh.abroad.data.source.firebase.FirebaseDb
import com.lyh.abroad.domain.entity.ChatRoomEntity
import com.lyh.abroad.domain.model.ResultModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object ChatRoomRemoteSource : ChatRoomSource {

    /* private */ const val TABLE_CHAT_ROOMS = "chatRooms"
    private const val CHATS = "chats"
    private const val MEMBERS = "members"
    private const val MEMBER_INFO = "memberInfo"
    private const val INFOS = "infos"

    private val db = FirebaseDb.getDatabase(TABLE_CHAT_ROOMS)

    override suspend fun getChatRoomList(uid: String): ResultModel<List<ChatRoomEntity>> {
        TODO()
    }

    override suspend fun setChatRoom(chatRoomEntity: ChatRoomEntity): ResultModel<Unit> {
        setChatMember(chatRoomEntity)
        return setChats(chatRoomEntity)
    }

    override suspend fun addChatRoomUser(uid: String, chattingRoomId: String): ResultModel<Unit> =
        suspendCancellableCoroutine { continuation ->
            // TODO 나 자신일 경우, true -> false로 바꾸는 로직임. 수정해야함.
            db.child(chattingRoomId)
                .updateChildren(mapOf("users" to mapOf(uid to false)))
                .addOnSuccessListener { continuation.resume(ResultModel.onSuccess(Unit)) }
                .addOnFailureListener { continuation.resume(ResultModel.onFailed(it)) }
        }

    private suspend fun setChatMember(chatRoomEntity: ChatRoomEntity): ResultModel<Unit> =
        suspendCancellableCoroutine { continuation ->
            db.child(MEMBERS)
                .child(chatRoomEntity.postId)
                .setValue(ChatRoomMemberDataMapper.toModel(chatRoomEntity))
                .addOnSuccessListener {
                    continuation.resume(ResultModel.onSuccess(Unit))
                }
                .addOnFailureListener {
                    continuation.resume(ResultModel.onFailed(it))
                }
        }

    private suspend fun setChats(chatRoomEntity: ChatRoomEntity): ResultModel<Unit> =
        suspendCancellableCoroutine { continuation ->
            db.child(CHATS)
                .child(chatRoomEntity.postId)
                .setValue(ChatDataMapper.toModel(chatRoomEntity))
                .addOnSuccessListener {
                    continuation.resume(ResultModel.onSuccess(Unit))
                }
                .addOnFailureListener {
                    continuation.resume(ResultModel.onFailed())
                }

        }

}
