package com.lyh.abroad.presenter.feed

import android.view.View
import android.widget.TextView
import com.lyh.abroad.R
import com.lyh.abroad.presenter.base.BaseViewHolder
import com.lyh.abroad.presenter.model.Feed

class FeedItemViewHolder(itemView: View) : BaseViewHolder<Feed>(itemView) {

    override fun bind(item: Feed) {
        itemView.findViewById<TextView>(R.id.feed_title).text = item.title
        itemView.findViewById<TextView>(R.id.feed_content).text = item.contents
        itemView.findViewById<TextView>(R.id.feed_writer_id).text = item.id
        itemView.findViewById<TextView>(R.id.feed_create_date).text = item.createDate
    }
}