package com.lyh.abroad.presenter.post


import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import com.lyh.abroad.R
import com.lyh.abroad.presenter.base.BaseFragment

class PostContainerFragment : BaseFragment(R.layout.fragment_post_container) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.commit {
            add(R.id.post_container, PostFragment())
        }
    }
}
