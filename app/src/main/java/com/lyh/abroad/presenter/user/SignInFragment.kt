package com.lyh.abroad.presenter.user


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentSignInBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.base.ViewModelFactory

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentSignInBinding.bind(view).apply {
            lifecycleOwner = viewLifecycleOwner
            signViewModel =
                viewModels<SignViewModel>({ this@SignInFragment }, ViewModelFactory::get).value

            viewModels<SignViewModel>({ this@SignInFragment }, ViewModelFactory::get).value.statusLiveData.observe(this@SignInFragment.viewLifecycleOwner) {
                when (it) {
                    BaseViewModel.Status.Success -> Toast.makeText(this@SignInFragment.requireContext(), "성공", Toast.LENGTH_LONG).show()
                }
            }
        }


    }


}
