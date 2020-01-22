package com.lyh.abroad.presenter.feed


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentFeedBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.base.listview.BaseAdapter
import com.lyh.abroad.presenter.base.listview.BaseListDivider
import com.lyh.abroad.presenter.model.Feed
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : BaseFragment(R.layout.fragment_feed) {

    private val feedViModel by viewModels<FeedViewModel> {
        ViewModelFactory.get(requireActivity().application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentFeedBinding.bind(view).apply {
            lifecycleOwner = viewLifecycleOwner
            feedViewModel = this@FeedFragment.feedViModel
        }

        rv_feed.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(BaseListDivider(16f))
            object : BaseAdapter<Feed, FeedItemViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                    FeedItemViewHolder(
                        LayoutInflater
                            .from(context)
                            .inflate(R.layout.item_feed_view, parent, false)
                    )
            }.also {
                adapter = it
            }
        }
    }
}
