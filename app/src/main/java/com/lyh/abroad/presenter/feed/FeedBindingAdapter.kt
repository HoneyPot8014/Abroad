package com.lyh.abroad.presenter.feed

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.presenter.base.listview.BaseAdapter
import com.lyh.abroad.presenter.model.Feed

@BindingAdapter("item")
fun RecyclerView.setList(list: List<Feed>?) {
    if (!list.isNullOrEmpty()) {
        (adapter as? BaseAdapter<Feed, FeedItemViewHolder>)?.submitList(list)
    }
}
