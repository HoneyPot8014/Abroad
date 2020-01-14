package com.lyh.abroad.presenter.base.signin


import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentSignInBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.BaseViewModel.Status.*
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.bottomnav.BottomNavViewModel
import com.lyh.abroad.presenter.custom.BottomNavigation
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private val bottomNavViewModel by activityViewModels<BottomNavViewModel>()
    private val signInViewModel by viewModels<SignInViewModel> {
        ViewModelFactory.get(requireActivity().application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBinding()
        showCardAnim()
        observeLoginStatus()
        sign_in_sign_up.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.sign_up_container, SignUpFragment())
                addToBackStack(null)
            }
        }
    }

    private fun setUpBinding() {
        FragmentSignInBinding.bind(view ?: return).apply {
            lifecycleOwner = viewLifecycleOwner
            signInViewModel = this@SignInFragment.signInViewModel
        }
    }

    private fun showCardAnim() {
        if (!signInViewModel.isAnimated) {
            ValueAnimator.ofFloat(400f, 0f).apply {
                addUpdateListener {
                    sign_in_card?.translationY = animatedValue as Float
                    sign_in_email_title?.translationY = animatedValue as Float
                    sign_in_email_input_layout?.translationY = animatedValue as Float
                    sign_in_password_title?.translationY = animatedValue as Float
                    sign_in_password_input_layout?.translationY = animatedValue as Float
                    sign_in_sign_in?.translationY = animatedValue as Float
                    sign_in_sign_up?.translationY = animatedValue as Float
                }
                duration = 400
                interpolator = AccelerateInterpolator()
                start()
            }
            signInViewModel.isAnimated = true
        }
    }

    private fun observeLoginStatus() {
        signInViewModel.statusLiveData.observe(this@SignInFragment.viewLifecycleOwner) {
            when (it) {
                Success -> {
                    bottomNavViewModel.currentNav.value = BottomNavigation.BottomNavItem.FEED
                    hidePg()
                }
                is Failed -> {
                    showSnackMessage(context?.getString(it.reason.message) ?: return@observe)
                    hidePg()
                }
                Loading -> showPg()
            }
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
