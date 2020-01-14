package com.lyh.abroad.presenter.base.signin


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentSignUpBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.BaseViewModel.Status.Failed
import com.lyh.abroad.presenter.base.BaseViewModel.Status.Success
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.place.CountrySelectFragment
import com.lyh.abroad.presenter.place.PlaceViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding

    companion object {
        private const val GALLERY_PICK = 1000
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBinding()
        add_profile.setOnClickListener {
            startIntentForProfilePick()
        }
        search_nation.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.sign_up_container, CountrySelectFragment())
                addToBackStack(null)
            }
        }
        sign_up_cancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        observeSignUpStatus()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_PICK) {
            if (resultCode == RESULT_OK) {
                binding.signUpViewModel?.setProfileUri(data?.data)
            }
        }
    }

    private fun setUpBinding() {
        binding = FragmentSignUpBinding.bind(view ?: return).apply {
            lifecycleOwner = viewLifecycleOwner
            requireActivity().application.also {
                signUpViewModel = viewModels<SignUpViewModel> { ViewModelFactory.get(it) }.value
                placeViewModel = viewModels<PlaceViewModel> ({parentFragment ?: this@SignUpFragment}, { ViewModelFactory.get(it) }).value
            }
        }
    }

    private fun observeSignUpStatus() {
        binding.signUpViewModel?.statusLiveData?.observe(viewLifecycleOwner) {
            when (it) {
                Success -> TODO()
                is Failed -> {
                    showSnackMessage(context?.getString(it.reason.message) ?: return@observe)
                }
            }
        }
    }

    private fun startIntentForProfilePick() {
        //TODO 커스텀 구현? 라이브러리 활용?
        Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }.let {
            startActivityForResult(
                it,
                GALLERY_PICK
            )
        }
    }
}
