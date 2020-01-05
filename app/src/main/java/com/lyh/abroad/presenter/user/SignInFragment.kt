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
import com.lyh.abroad.presenter.user.SignViewModel.FailReason
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    private var isAnimated = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            signViewModel =
                activityViewModels<SignViewModel>(ViewModelFactory::get).value
        }
    }

    private fun showCardAnim() {
        if (!isAnimated) {
            ValueAnimator.ofFloat(500f, 0f).apply {
                addUpdateListener {
                    sign_in_card?.translationY = animatedValue as Float
                }
                duration = 600
                interpolator = AccelerateInterpolator()
                start()
            }
            isAnimated = !isAnimated
        }
    }

    private fun observeLoginStatus() {
        binding.signViewModel?.statusLiveData?.observe(this@SignInFragment.viewLifecycleOwner) {
            when (it) {
                Success -> TODO()
                Loading -> TODO()
                is Failed -> {
                    if (it.reason is FailReason) {
                        showSnackMessage(context?.getString(it.reason.message) ?: return@observe)
                    }
                }
            }
        }
    }
}
