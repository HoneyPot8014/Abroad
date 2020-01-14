package com.lyh.abroad.presenter.bottomnav

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.google.firebase.auth.FirebaseAuth
import com.lyh.abroad.R
import com.lyh.abroad.databinding.ActivityBottomNavBinding
import com.lyh.abroad.presenter.base.BaseViewModel.Status.Failed
import com.lyh.abroad.presenter.base.BaseViewModel.Status.Success
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.base.signin.SignInContainerFragment
import com.lyh.abroad.presenter.base.signin.SignInFragment
import com.lyh.abroad.presenter.custom.BottomNavigation.BottomNavItem.*
import com.lyh.abroad.presenter.feed.FeedFragment
import com.lyh.abroad.presenter.post.PostFragment
import com.lyh.abroad.presenter.user.UserViewModel
import kotlinx.android.synthetic.main.activity_bottom_nav.*

class BottomNavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavBinding
    private lateinit var bottomNavViewModel: BottomNavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseAuth.getInstance().signOut()
        bottomNavViewModel = viewModels<BottomNavViewModel>().value
        binding =
            DataBindingUtil.setContentView<ActivityBottomNavBinding>(
                this,
                R.layout.activity_bottom_nav
            )
                .apply {
                    userViewModel = viewModels<UserViewModel>{ViewModelFactory.get(application)}.value
                }
        binding.userViewModel?.statusLiveData?.observe(this) {
            when (it) {
                Success -> bottomNavViewModel.currentNav.value = FEED
                is Failed -> bottomNavViewModel.currentNav.value = null
                else -> Unit
            }
        }

        bottom_nav.selectedLiveData.observe(this) {
            if (bottomNavViewModel.currentNav.value != it) {
                bottomNavViewModel.currentNav.value = it
                bottom_nav.setSelected(it)
            }
        }

        bottomNavViewModel.currentNav.observe(this) {
            val fragment = when (it) {
                FEED -> FeedFragment()
                CHATTING -> null
                POST -> PostFragment()
                ALARM -> null
                MY_PAGE -> null
                else -> SignInContainerFragment()
            }
            if (fragment is SignInFragment) hideBottomNav() else showBottomNav()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment ?: return@observe)
                .commit()
        }
    }

    private fun hideBottomNav() {
        if (bottom_nav?.visibility == VISIBLE) {
            bottom_nav?.visibility = GONE
        }
    }

    private fun showBottomNav() {
        if (bottom_nav?.visibility == GONE) {
            bottom_nav?.visibility = VISIBLE
        }
    }
}
