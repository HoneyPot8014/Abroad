package com.lyh.abroad.presenter.user


import android.os.Bundle
import android.view.View
import com.lyh.abroad.R
import com.lyh.abroad.presenter.MainActivity
import com.lyh.abroad.presenter.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setSupportActionBar(toolbar)
    }


}
