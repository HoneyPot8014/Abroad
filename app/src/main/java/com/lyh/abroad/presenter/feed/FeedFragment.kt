package com.lyh.abroad.presenter.feed


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentFeedBinding
//import com.lyh.abroad.databinding.FragmentFeedBinding
import com.lyh.abroad.presenter.base.BaseAdapter
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.model.Feed
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : BaseFragment(R.layout.fragment_feed) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentFeedBinding.bind(view).apply {
            feedViewModel = activityViewModels<FeedViewModel>(ViewModelFactory::get).value
            lifecycleOwner = viewLifecycleOwner
        }

        rv_feed.apply {
            adapter = object : BaseAdapter<Feed, FeedItemViewHolder>() {

                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): FeedItemViewHolder {
                    return FeedItemViewHolder(
                        LayoutInflater.from(context).inflate(
                            R.layout.item_feed_view, parent, false
                        )
                    )
                }
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }
}
