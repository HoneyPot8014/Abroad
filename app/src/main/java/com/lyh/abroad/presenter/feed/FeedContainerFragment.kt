package com.lyh.abroad.presenter.feed


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.lyh.abroad.R
import com.lyh.abroad.presenter.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class FeedContainerFragment : BaseFragment(R.layout.fragment_feed_container) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.commit {
            replace(R.id.feed_container, FeedFragment())
        }
    }
}
