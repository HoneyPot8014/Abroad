package com.lyh.abroad.presenter.feed


import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentFeedDetailBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.user.UserViewModel

class FeedDetailFragment : BaseFragment(R.layout.fragment_feed_detail) {

    private val feedViewModel by viewModels<FeedViewModel>(
        { parentFragment ?: this },
        { ViewModelFactory.get(requireActivity().application) }
    )
    private val userViewModel by viewModels<UserViewModel> {
        ViewModelFactory.get(requireActivity().application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatcher?.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        setUpBinding()
    }

    private fun setUpBinding() {
        FragmentFeedDetailBinding.bind(view ?: return).apply {
            lifecycleOwner = viewLifecycleOwner
            feedViewModel = this@FeedDetailFragment.feedViewModel
            userViewModel = this@FeedDetailFragment.userViewModel
        }
    }

}
