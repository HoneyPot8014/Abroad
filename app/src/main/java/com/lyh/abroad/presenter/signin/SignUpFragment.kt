package com.lyh.abroad.presenter.signin


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentSignUpBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.BaseViewModel.Status.*
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.place.PlaceViewModel
import com.lyh.abroad.presenter.place.country.CountrySelectFragment
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private val signUpViewModel by viewModels<SignUpViewModel> {
        ViewModelFactory.get(requireActivity().application)
    }
    private val placeViewModel by viewModels<PlaceViewModel>(
        { parentFragment ?: this@SignUpFragment },
        { ViewModelFactory.get(requireActivity().application) }
    )

    companion object {
        private const val GALLERY_PICK = 1000
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatcher?.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        hideBottomNav()
        setUpBinding()
        add_profile.setOnClickListener {
            startIntentForProfilePick()
        }
        search_nation.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    R.id.sign_up_container,
                    CountrySelectFragment()
                )
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
                signUpViewModel.setProfileUri(data?.data)
            }
        }
    }

    private fun setUpBinding() {
        FragmentSignUpBinding.bind(view ?: return).apply {
            lifecycleOwner = viewLifecycleOwner
            signUpViewModel = this@SignUpFragment.signUpViewModel
            placeViewModel = this@SignUpFragment.placeViewModel
        }
    }

    private fun observeSignUpStatus() {
        signUpViewModel.statusLiveData.observe(viewLifecycleOwner) {
            when (it) {
                Success -> {
                    hidePg()
                    parentFragmentManager.popBackStack()
                }
                Loading -> {
                    showPg()
                }
                is Failed -> {
                    hidePg()
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

    private fun showPg() {
        if (pg?.visibility == View.INVISIBLE) {
            pg?.visibility = View.VISIBLE
        }
    }

    private fun hidePg() {
        if (pg?.visibility == View.VISIBLE) {
            pg?.visibility = View.INVISIBLE
        }
    }
}
