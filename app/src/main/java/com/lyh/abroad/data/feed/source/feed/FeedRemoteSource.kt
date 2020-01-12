package com.lyh.abroad.data.feed.source.feed

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lyh.abroad.data.feed.model.FeedDataModel
import com.lyh.abroad.domain.entity.FeedEntity
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
                                placeId.children.mapNotNull { feed ->
                                    feed.getValue(FeedDataModel::class.java)
                                }[0]
                            }
                        )
                    }
                })
        }

    override suspend fun setFeed(feedEntity: FeedEntity) {

    }

}
