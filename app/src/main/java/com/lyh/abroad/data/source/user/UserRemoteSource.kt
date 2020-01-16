package com.lyh.abroad.data.source.user

import android.net.Uri
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.lyh.abroad.data.model.UserDataModel
import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.model.ResultModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object UserRemoteSource : UserSource {

    private val db = FirebaseDatabase.getInstance().getReference("users")
    private val storage = FirebaseStorage.getInstance().getReference("userProfileImage")

    override suspend fun fetchUser(uid: String): ResultModel<UserDataModel> =
        suspendCancellableCoroutine {
            db.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    it.resume(ResultModel.onFailed(p0.toException()))
                }

                override fun onDataChange(p0: DataSnapshot) {
                    it.resume(ResultModel.onSuccess(p0.getValue(UserDataModel::class.java)))
                }
            })
        }

    override suspend fun setUser(userEntity: UserEntity): ResultModel<Unit> {
        return suspendCancellableCoroutine { continuation ->
            db.child(userEntity.uid).setValue(userEntity)
                .addOnSuccessListener {
                    continuation.resume(ResultModel.onSuccess(Unit))
                }
                .addOnFailureListener {
                    continuation.resume(ResultModel.onFailed(it))
                }
        }
    }

    override suspend fun setProfile(uid: String, uri: Uri): ResultModel<String> {
        return suspendCancellableCoroutine { continuation ->
            storage.child(uid).apply {
                putFile(uri).continueWithTask {
                    downloadUrl
                }.addOnSuccessListener {
                    continuation.resume(ResultModel.onSuccess(it.toString()))
                }.addOnFailureListener {
                    continuation.resume(ResultModel.onFailed(it))
                }
            }
        }
    }
}
