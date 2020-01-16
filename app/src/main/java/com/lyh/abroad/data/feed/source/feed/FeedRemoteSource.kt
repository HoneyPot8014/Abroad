package com.lyh.abroad.data.feed.source.feed

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lyh.abroad.data.feed.model.FeedDataModel
import com.lyh.abroad.data.feed.model.PostDataModel
import com.lyh.abroad.domain.model.ResultModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

object FeedRemoteSource : FeedSource {

    private val db = FirebaseDatabase.getInstance().getReference("bulletinBoard")

    override suspend fun fetchFeedList(countryCode: String): List<FeedDataModel> =
        suspendCancellableCoroutine {
            db.child(countryCode)
                .orderByChild("createDate")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        it.cancel(p0.toException())
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        it.resume(
                            p0.children.mapNotNull { placeId ->
                                placeId.children
                                    .mapNotNull { feed ->
                                        feed.getValue(FeedDataModel::class.java)?.apply {
                                            countryId = countryCode
                                            cityId = placeId.key
                                        }
                                    }
                            }.flatten()
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
