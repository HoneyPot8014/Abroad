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

    override suspend fun setChatRoom(uid: String, chattingRoomId: String): ResultModel<Unit> {
        return suspendCancellableCoroutine { continuation ->
            val dbRef = db.child(chattingRoomId)
            if (dbRef.key == null) {
                continuation.resume(ResultModel.onFailed())
            } else {
                dbRef
                    .updateChildren(mapOf("users" to mapOf(uid to false)))
                    .addOnSuccessListener { continuation.resume(ResultModel.onSuccess(Unit)) }
                    .addOnFailureListener { continuation.resume(ResultModel.onFailed(it)) }
            }
        }
    }

}
