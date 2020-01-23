package com.lyh.abroad.presenter.feed.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lyh.abroad.R
import com.lyh.abroad.presenter.base.BaseFragment

class FeedDetailFragment : BaseFragment(R.layout.fragment_feed_detail) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed_detail, container, false)
    }


}
