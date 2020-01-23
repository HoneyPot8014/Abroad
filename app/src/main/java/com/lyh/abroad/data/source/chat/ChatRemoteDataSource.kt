package com.lyh.abroad.data.source.chat

import com.google.firebase.database.FirebaseDatabase
import com.lyh.abroad.domain.model.ResultModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object ChatRemoteDataSource : ChatDataSource {

    private val db = FirebaseDatabase.getInstance().getReference("chatRooms")

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

    override suspend fun setChatRoom(uid: String, chattingRoomId: String): ResultModel<Unit> =
        suspendCancellableCoroutine { continuation ->
            // TODO 나 자신일 경우, true -> false로 바꾸는 로직임. 수정해야함.
            db.child(chattingRoomId)
                .updateChildren(mapOf("users" to mapOf(uid to false)))
                .addOnSuccessListener { continuation.resume(ResultModel.onSuccess(Unit)) }
                .addOnFailureListener { continuation.resume(ResultModel.onFailed(it)) }
        }
}
