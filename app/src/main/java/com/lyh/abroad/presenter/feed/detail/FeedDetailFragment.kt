package com.lyh.abroad.presenter.feed.detail


import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.commit
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentFeedDetailBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.model.Feed
import com.lyh.abroad.presenter.user.detail.UserDetailFragment
import kotlinx.android.synthetic.main.fragment_feed_detail.*

class FeedDetailFragment : BaseFragment(R.layout.fragment_feed_detail) {

    private lateinit var binding: FragmentFeedDetailBinding

    companion object {

        fun newInstance(feed: Feed) =
            FeedDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("feed", feed)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatcher?.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        hideBottomNav()
        setUpBinding()
        user_name.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.feed_container, UserDetailFragment.newInstance(binding.feed?.id ?: ""))
                addToBackStack(null)
            }
        }
        back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setUpBinding() {
        binding = FragmentFeedDetailBinding.bind(view ?: return).apply {
            lifecycleOwner = viewLifecycleOwner
            feed = arguments?.getParcelable("feed")
        }
    }
}
