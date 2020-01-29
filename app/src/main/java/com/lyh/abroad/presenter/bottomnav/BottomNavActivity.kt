package com.lyh.abroad.presenter.bottomnav

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import com.lyh.abroad.R
import com.lyh.abroad.databinding.ActivityBottomNavBinding
import com.lyh.abroad.presenter.base.BaseActivity
import com.lyh.abroad.presenter.base.BaseViewModel.Status.Failed
import com.lyh.abroad.presenter.base.BaseViewModel.Status.Success
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.custom.BottomNavigation.BottomNavItem.*
import com.lyh.abroad.presenter.feed.FeedContainerFragment
import com.lyh.abroad.presenter.post.PostContainerFragment
import com.lyh.abroad.presenter.signin.SignInContainerFragment
import com.lyh.abroad.presenter.user.UserViewModel
import kotlinx.android.synthetic.main.activity_bottom_nav.*

class BottomNavActivity : BaseActivity() {

    private lateinit var bottomNavViewModel: BottomNavViewModel
    private val userViewModel by viewModels<UserViewModel> { ViewModelFactory.get(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavViewModel = viewModels<BottomNavViewModel>().value
        DataBindingUtil.setContentView<ActivityBottomNavBinding>(this, R.layout.activity_bottom_nav)
            .apply {
                lifecycleOwner = this@BottomNavActivity
                userViewModel = this@BottomNavActivity.userViewModel
                bottomNavViewModel = this@BottomNavActivity.bottomNavViewModel
            }
        userViewModel.statusLiveData.observe(this) {
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
                FEED -> FeedContainerFragment()
                CHATTING -> null
                POST -> PostContainerFragment()
                ALARM -> null
                MY_PAGE -> null
                else -> SignInContainerFragment()
            }
            supportFragmentManager.commit {
                replace(
                    R.id.fragment_container,
                    fragment ?: return@observe,
                    fragment.javaClass.name
                )
            }
        }
    }
}
