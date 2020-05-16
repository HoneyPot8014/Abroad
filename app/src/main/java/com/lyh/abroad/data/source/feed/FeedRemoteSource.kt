package com.lyh.abroad.data.source.feed

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.lyh.abroad.data.model.FeedDataModel
import com.lyh.abroad.data.model.PostDataModel
import com.lyh.abroad.data.source.firebase.FirebaseDb
import com.lyh.abroad.domain.model.ResultModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object FeedRemoteSource : FeedSource {

    /* private */ const val TABLE_BOARD = "bulletinBoard"
    private const val ALL_POSTS = "allPosts"

    private val db = FirebaseDb.getDatabase(TABLE_BOARD)

    override suspend fun fetchFeedList(countryCode: String): List<FeedDataModel> =
        suspendCancellableCoroutine { continuation ->
            db.child(countryCode)
                .child(ALL_POSTS)
                .orderByChild("createDate")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        continuation.cancel(p0.toException())
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        continuation.resume(
                            p0.children.mapNotNull { it.getValue(FeedDataModel::class.java) }
                        )
                    }
                })
        }

    override suspend fun setFeed(
        countryId: String,
        cityId: String,
        postDataModel: PostDataModel
    ): ResultModel<Unit> {
        val key = db.child(countryId)
            .child(ALL_POSTS)
            .push()
            .key
        return if (key == null) {
            ResultModel.onFailed()
        } else {
            val value = postDataModel.copy(postId = key)
            setAllPosts(key, countryId, value)
            setCountryPosts(key, countryId, cityId, value)
        }
    }

    private suspend fun setAllPosts(
        key: String,
        countryId: String,
        postDataModel: PostDataModel
    ): ResultModel<Unit> = suspendCancellableCoroutine { continuation ->
        db.child(countryId)
            .child(ALL_POSTS)
            .child(key)
            .setValue(postDataModel)
            .addOnSuccessListener {
                continuation.resume(ResultModel.onSuccess(Unit))
            }
            .addOnFailureListener {
                continuation.resume(ResultModel.onFailed())
            }
    }

    private suspend fun setCountryPosts(
        key: String,
        countryId: String,
        cityId: String,
        postDataModel: PostDataModel
    ): ResultModel<Unit> =
        suspendCancellableCoroutine { continuation ->
            db.child(countryId)
                .child(cityId)
                .child(key)
                .setValue(postDataModel)
                .addOnSuccessListener {
                    continuation.resume(ResultModel.onSuccess(Unit))
                }
                .addOnFailureListener {
                    continuation.resume(ResultModel.onFailed())
                }
        }
}
