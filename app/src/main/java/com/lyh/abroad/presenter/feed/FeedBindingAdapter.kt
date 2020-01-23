package com.lyh.abroad.presenter.feed

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lyh.abroad.presenter.base.listview.BaseAdapter
import com.lyh.abroad.presenter.feed.list.FeedItemViewHolder
import com.lyh.abroad.presenter.model.Feed

@BindingAdapter("item")
fun RecyclerView.setList(list: List<Feed>?) {
    if (!list.isNullOrEmpty()) {
        (adapter as? BaseAdapter<Feed, FeedItemViewHolder>)?.submitList(list)
    }
}

@BindingAdapter("setProfile")
fun ImageView.setProfile(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}
