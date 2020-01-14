package com.lyh.abroad.presenter.base.signin


import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import com.lyh.abroad.R
import com.lyh.abroad.presenter.base.BaseFragment

class SignInContainerFragment : BaseFragment(R.layout.fragment_sign_in_container) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.commit {
            replace(R.id.sign_up_container, SignInFragment())
        }
    }
}
