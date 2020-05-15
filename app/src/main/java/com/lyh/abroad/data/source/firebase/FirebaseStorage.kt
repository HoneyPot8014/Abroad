package com.lyh.abroad.data.source.firebase

import com.google.firebase.storage.FirebaseStorage
import com.lyh.abroad.BuildConfig

object FirebaseStorage {

    private const val PHASE_TEST = "test"
    private const val PHASE_DEV = "dev"

    fun getStorage(name: String) =
        if (BuildConfig.DEBUG) {
            FirebaseStorage.getInstance()
                .getReference(PHASE_TEST)
                .child(name)
        } else {
            FirebaseStorage.getInstance()
                .getReference(name)
        }
}