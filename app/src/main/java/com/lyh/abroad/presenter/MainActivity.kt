package com.lyh.abroad.presenter

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.lyh.abroad.R
import com.lyh.abroad.databinding.ActivityMainBinding
import com.lyh.abroad.presenter.base.BaseViewModel.Status.Failed
import com.lyh.abroad.presenter.base.BaseViewModel.Status.Success
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.custom.BottomNavigation.BottomNavItem.*
import com.lyh.abroad.presenter.feed.FeedFragment
import com.lyh.abroad.presenter.user.SignInFragment
import com.lyh.abroad.presenter.user.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
                .apply {
                    userViewModel = viewModels<UserViewModel>(ViewModelFactory::get).value
                }
        binding.userViewModel?.userLiveData?.observe(this) {

        }
        binding.userViewModel?.statusLiveData?.observe(this) {
            val fragment = when (it) {
                Success -> {
                    showBottomNav()
                    FeedFragment()
                }
                is Failed -> {
                    hideBottomNav()
                    SignInFragment()
                }
                else -> null
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment ?: return@observe)
                .commit()
        }


        bottom_nav.seletedLiveData.observe(this) {
            val fragment = when (it) {
                FEED -> FeedFragment()
                CHATTING -> TODO()
                POST -> TODO()
                ALARM -> TODO()
                MY_PAGE -> TODO()
                else -> TODO()
            }

            if (binding.userViewModel?.userLiveData?.value != null) {
                showBottomNav()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            }

        }
    }

    fun hideBottomNav() {
        if (bottom_nav.visibility == VISIBLE) {
            bottom_nav.visibility = GONE
        }
    }

    fun showBottomNav() {
        if (bottom_nav.visibility == GONE) {
            bottom_nav.visibility = VISIBLE
        }
    }
}
