package com.lyh.abroad.data.feed.source.user

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.lyh.abroad.domain.model.ResultModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object UserAuth {

    private val auth = FirebaseAuth.getInstance()

    suspend fun createUser(email: String, password: String) {
//        suspendCancellableCoroutine<> { suspend ->
//            auth.createUserWithEmailAndPassword(email, password)
//                .addOnSuccessListener {
//                    it.user
//                }
//                .addOnCanceledListener {
//                    suspend.cancel()
//                }
//                .addOnFailureListener {
//                    suspend.cancel(it)
//                }
//        }
    }

    suspend fun signIn(email: String, password: String): ResultModel<AuthResult> =
        suspendCancellableCoroutine { suspend ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    suspend.resume(ResultModel.onSuccess(it))
                }
                .addOnCanceledListener {
                    suspend.resume(ResultModel.onFailed())
                }
                .addOnFailureListener {
                    suspend.resume(ResultModel.onFailed(it))
                }
        }
}