package com.lyh.abroad.data.source.chatroom

import com.lyh.abroad.data.source.firebase.FirebaseDb
import com.lyh.abroad.domain.entity.ChatRoomEntity
import com.lyh.abroad.domain.model.ResultModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object ChatRoomRemoteSource : ChatRoomSource {

    /* private */ const val TABLE_CHAT_ROOMS = "chatRooms"

    private val db = FirebaseDb.getDatabase(TABLE_CHAT_ROOMS)

    override suspend fun getChatRoomList(uid: String): ResultModel<List<ChatRoomEntity>> {
//        suspendCancellableCoroutine<> { continuation ->
//
//        }
        TODO()
    }

    override suspend fun setChatRoom(uid: String): ResultModel<String> =
        suspendCancellableCoroutine { continuation ->
            val dbRef = db.push()
            if (dbRef.key == null) {
                continuation.resume(ResultModel.onFailed())
            } else {
                dbRef
                    .updateChildren(mapOf("users" to mapOf(uid to true)))
                    .addOnSuccessListener { continuation.resume(ResultModel.onSuccess(dbRef.key)) }
                    .addOnFailureListener { continuation.resume(ResultModel.onFailed(it)) }
            }
        }

    override suspend fun addChatRoomUser(uid: String, chattingRoomId: String): ResultModel<Unit> =
        suspendCancellableCoroutine { continuation ->
            // TODO 나 자신일 경우, true -> false로 바꾸는 로직임. 수정해야함.
            db.child(chattingRoomId)
                .updateChildren(mapOf("users" to mapOf(uid to false)))
                .addOnSuccessListener { continuation.resume(ResultModel.onSuccess(Unit)) }
                .addOnFailureListener { continuation.resume(ResultModel.onFailed(it)) }
        }

}
