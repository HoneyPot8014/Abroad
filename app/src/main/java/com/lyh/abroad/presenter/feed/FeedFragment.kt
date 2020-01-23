package com.lyh.abroad.presenter.feed


import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentFeedBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.base.listview.BaseListDivider
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
            adapter = FeedListAdapter().apply {
                setOnClickListener {
                    parentFragmentManager.commit {
                        replace(R.id.feed_container, FeedDetailFragment())
                        addToBackStack(null)
                    }
                }
            }
        }
    }
}
