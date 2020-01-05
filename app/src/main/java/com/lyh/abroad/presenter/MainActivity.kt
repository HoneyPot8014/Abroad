package com.lyh.abroad.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.lyh.abroad.R
import com.lyh.abroad.presenter.custom.BottomNavigation.BottomNavItem.*
import com.lyh.abroad.presenter.feed.FeedFragment
import com.lyh.abroad.presenter.user.SignInFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav.seletedLiveData.observe(this) {
            // FEED, CHATTING, POST, ALARM, MY_PAGE
            val fragment = when (it) {
                FEED -> FeedFragment()
                CHATTING -> TODO()
                POST -> TODO()
                ALARM -> TODO()
                MY_PAGE -> SignInFragment()
                else -> TODO()
            }

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}
