package com.lyh.abroad.data.source.chat

import com.google.firebase.database.FirebaseDatabase

object ChatRemoteDataSource : ChatDataSource {

    private val db = FirebaseDatabase.getInstance().getReference("chatRooms")

}
