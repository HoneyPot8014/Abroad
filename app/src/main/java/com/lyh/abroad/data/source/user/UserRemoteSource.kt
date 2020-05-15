package com.lyh.abroad.data.source.user

import android.net.Uri
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.lyh.abroad.data.model.UserDataModel
import com.lyh.abroad.data.source.firebase.FirebaseDb
import com.lyh.abroad.data.source.firebase.FirebaseStorage
import com.lyh.abroad.domain.entity.UserEntity
import com.lyh.abroad.domain.model.ResultModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object UserRemoteSource : UserSource {

    /* private */ const val TABLE_USER = "users"
    /* private */ const val STORAGE_USER_PROFILE_IMAGE = "userProfileImage"

    private val db = FirebaseDb.getDatabase(TABLE_USER)
    private val storage = FirebaseStorage.getStorage(STORAGE_USER_PROFILE_IMAGE)

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
