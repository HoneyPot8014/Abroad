package com.lyh.abroad.data.source.firebase

import com.google.firebase.database.FirebaseDatabase
import com.lyh.abroad.BuildConfig

object FirebaseDb {

    private const val PHASE_TEST = "test"
    private const val PHASE_DEV = "dev"

    fun getDatabase(table: String) =
        if (BuildConfig.DEBUG) {
            FirebaseDatabase.getInstance()
                .getReference(PHASE_TEST)
                .child(table)
        } else {
            FirebaseDatabase.getInstance()
                .getReference(table)
        }

}

