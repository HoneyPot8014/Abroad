package com.lyh.abroad.presenter.feed


import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lyh.abroad.R
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.base.BaseFragment

class FeedFragment : BaseFragment(R.layout.fragment_feed) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModels<FeedViewModel>(ViewModelFactory::get).value
    }


}
