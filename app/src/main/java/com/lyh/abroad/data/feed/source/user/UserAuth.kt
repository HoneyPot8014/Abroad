package com.lyh.abroad.data.feed.source.user

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.lyh.abroad.domain.model.ResultModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object UserAuth {

    private val auth = FirebaseAuth.getInstance()

    fun getUid() = auth.uid?.let {
        ResultModel.onSuccess(it)
    } ?: ResultModel.onFailed()

    suspend fun createUser(email: String, password: String): ResultModel<AuthResult> {
        return suspendCancellableCoroutine { suspend ->
            auth.createUserWithEmailAndPassword(email, password)
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

    fun getUserUid(): ResultModel<String> =
        if (auth.currentUser != null) {
            ResultModel.onSuccess(auth.uid)
        } else {
            ResultModel.onFailed()
        }
}