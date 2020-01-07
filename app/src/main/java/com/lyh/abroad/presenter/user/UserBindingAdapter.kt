package com.lyh.abroad.presenter.user

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("profileUri")
fun ImageView.setProfile(uri: Uri?) {
    uri?.also {
        Glide.with(context)
            .load(it)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    }
}