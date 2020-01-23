package com.lyh.abroad.presenter.user.detail


import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentUserDetailBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_user_detail.*

class UserDetailFragment : BaseFragment(R.layout.fragment_user_detail) {

    private val userDetailViewModel by viewModels<UserDetailViewModel> {
        ViewModelFactory.get(requireActivity().application)
    }

    companion object {

        fun newInstance(uid: String) =
            UserDetailFragment().apply {
                arguments = Bundle().apply { putString("uid", uid) }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatcher?.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        hideBottomNav()
        setUpBinding()
        // TODO 로직 변경
        userDetailViewModel.setUser(arguments!!.getString("uid")!!)
        cancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setUpBinding() {
        FragmentUserDetailBinding.bind(view ?: return).apply {
            lifecycleOwner = viewLifecycleOwner
            userDetailViewModel = this@UserDetailFragment.userDetailViewModel
        }
    }

}
