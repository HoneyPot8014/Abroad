package com.lyh.abroad.data.source.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.lyh.abroad.domain.model.ResultModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object AuthRemoteSource : AuthSource {

    private val auth = FirebaseAuth.getInstance()

    override suspend fun fetchNewUserId(email: String, password: String): ResultModel<String> =
        suspendCancellableCoroutine { suspend ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    it.user?.uid?.let { uid -> suspend.resume(ResultModel.onSuccess(uid)) }
                        ?: suspend.resume(ResultModel.onFailed())
                }
                .addOnCanceledListener {
                    suspend.resume(ResultModel.onFailed())
                }
                .addOnFailureListener {
                    suspend.resume(ResultModel.onFailed(it))
                }
        }

    override suspend fun fetchAuth(email: String, password: String): ResultModel<String> =
        suspendCancellableCoroutine { suspend ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    it.user?.uid?.let { uid ->
                        suspend.resume(ResultModel.onSuccess(uid))
                    } ?: kotlin.run { suspend.resume(ResultModel.onFailed()) }
                }
                .addOnCanceledListener {
                    suspend.resume(ResultModel.onFailed())
                }
                .addOnFailureListener {
                    suspend.resume(ResultModel.onFailed(it))
                }
        }

    override suspend fun fetchFmcToken(): ResultModel<String> {
        return suspendCancellableCoroutine { suspend ->
            FirebaseInstanceId.getInstance().instanceId
                .addOnSuccessListener {
                    suspend.resume(ResultModel.onSuccess(it.token))
                }
                .addOnFailureListener {
                    suspend.resume(ResultModel.onFailed(it))
                }
        }
    }

    override suspend fun fetchId(): ResultModel<String> =
        if (auth.currentUser != null) {
            ResultModel.onSuccess(auth.uid)
        } else {
            ResultModel.onFailed()
        }
}
