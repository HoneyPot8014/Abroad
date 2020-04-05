package com.lyh.abroad.data.source.feed

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lyh.abroad.data.model.FeedDataModel
import com.lyh.abroad.data.model.PostDataModel
import com.lyh.abroad.domain.model.ResultModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object FeedRemoteSource : FeedSource {

    private val db = FirebaseDatabase.getInstance().getReference("bulletinBoard")

    override suspend fun fetchFeedList(countryCode: String): List<FeedDataModel> =
        suspendCancellableCoroutine { continuation ->
            db.child(countryCode)
                .child("allPosts")
                .orderByChild("createDate")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        continuation.cancel(p0.toException())
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val a = p0.children.iterator()
                        while (a.hasNext()) {
                            Log.d("용현2", a.next().toString())
                        }
                        continuation.resume(
                            p0.children.mapNotNull {
                                it.getValue(FeedDataModel::class.java)
                            }
//                            p0.children.mapNotNull { placeId ->
//                                placeId.children
//                                    .mapNotNull { feed ->
//                                        feed.getValue(FeedDataModel::class.java)?.apply {
//                                            countryId = countryCode
//                                            cityId = placeId.key
//                                        }
//                                    }
//                            }.flatten()
                        )
                    }
                })
        }

    override suspend fun setFeed(countryId: String, cityId: String, postDataModel: PostDataModel): ResultModel<Unit> {
        return suspendCancellableCoroutine {continuation ->
            db.child(countryId)
                .child(cityId)
                .push()
                .setValue(postDataModel)
                .addOnSuccessListener {
                    continuation.resume(ResultModel.onSuccess(Unit))
                }
                .addOnFailureListener {
                    continuation.resume(ResultModel.onFailed())
                }
        }
    }
}
