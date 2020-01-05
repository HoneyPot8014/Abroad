package com.lyh.abroad.data.feed.source.user

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lyh.abroad.data.feed.model.UserDataModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object UserRemoteSource : UserSource {

    private val db = FirebaseDatabase.getInstance().getReference("users")

    override suspend fun fetchUser(uid: String): UserDataModel? {
        return suspendCancellableCoroutine {
            db.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    it.cancel(p0.toException())
                }

                override fun onDataChange(p0: DataSnapshot) {
                    it.resume(p0.getValue(UserDataModel::class.java))
                }
            })
        }
    }

    override suspend fun setUser(userEntity: UserDataModel) {

    }

}