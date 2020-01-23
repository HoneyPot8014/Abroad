package com.lyh.abroad.presenter.feed.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lyh.abroad.R
import com.lyh.abroad.presenter.base.listview.BaseAdapter
import com.lyh.abroad.presenter.model.Feed

class FeedListAdapter : BaseAdapter<Feed, FeedItemViewHolder>() {

    private var onClickListener: ((Feed) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder =
        LayoutInflater.from(parent.context).inflate(R.layout.item_feed_view, parent, false).let {
            FeedItemViewHolder(it)
        }

    override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnClickListener {
            Log.d("용현", "position: $position, item : ${getItem(position)}")
            onClickListener?.invoke(getItem(position))
        }
    }

    fun setOnClickListener(listener: (Feed) -> Unit) {
        onClickListener = listener
    }
}
