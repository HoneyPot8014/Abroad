package com.lyh.abroad.presenter.user.detail


import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentUserDetailBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.model.Feed
import com.lyh.abroad.presenter.user.UserViewModel

class UserDetailFragment : BaseFragment(R.layout.fragment_user_detail) {

    private val userViewModel by viewModels<UserViewModel> {
        ViewModelFactory.get(requireActivity().application)
    }

    companion object {

        fun newInstance(feed: Feed) =
            UserDetailFragment().apply {
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
        setUpBinding()
    }

    private fun setUpBinding() {
        FragmentUserDetailBinding.bind(view ?: return).apply {
            lifecycleOwner = viewLifecycleOwner
            userViewModel = this@UserDetailFragment.userViewModel
        }
    }

}
