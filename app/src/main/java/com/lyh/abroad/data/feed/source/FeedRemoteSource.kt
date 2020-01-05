package com.lyh.abroad.data.feed.source

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

    override suspend fun fetchFeedList(countryCode: String, country: String): List<FeedDataModel> =
        suspendCancellableCoroutine {
            db.child(countryCode)
                .child(country)
                .orderByChild("createDate")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        it.cancel(p0.toException())
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        it.resume(
                            p0.children.mapNotNull { item ->
                                item.getValue(FeedDataModel::class.java)
                            }.toList()
                        )
                    }
                })
        }

    override suspend fun setFeed(feedEntity: FeedEntity) {

    }

}
