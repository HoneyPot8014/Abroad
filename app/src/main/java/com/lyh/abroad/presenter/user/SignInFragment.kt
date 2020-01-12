package com.lyh.abroad.presenter.user


import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.activityViewModels
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

    private lateinit var binding: FragmentSignInBinding
    private lateinit var bottomNavViewModel: BottomNavViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavViewModel = activityViewModels<BottomNavViewModel>().value
        setUpBinding()
        showCardAnim()
        observeLoginStatus()
        sign_in_sign_up.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container, SignUpFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    private fun setUpBinding() {
        binding = FragmentSignInBinding.bind(view ?: return).apply {
            lifecycleOwner = viewLifecycleOwner
            signInViewModel = activityViewModels<SignInViewModel>(ViewModelFactory::get).value
        }
    }

    private fun showCardAnim() {
        if (binding.signInViewModel?.isAnimated == false) {
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
            binding.signInViewModel?.isAnimated = true
        }
    }

    private fun observeLoginStatus() {
        binding.signInViewModel?.statusLiveData?.observe(this@SignInFragment.viewLifecycleOwner) {
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
