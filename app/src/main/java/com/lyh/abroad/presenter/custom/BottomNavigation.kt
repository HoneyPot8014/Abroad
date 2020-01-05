package com.lyh.abroad.presenter.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.R
import com.lyh.abroad.presenter.custom.BottomNavigation.BottomNavItem.*
import kotlinx.android.synthetic.main.bottom_navigation.view.*

class BottomNavigation(
    context: Context?,
    attrs: AttributeSet?
) : LinearLayout(context, attrs) {

    private val _selectedLiveData = MutableLiveData<BottomNavItem>(FEED)
    val seletedLiveData
        get() = _selectedLiveData

    init {
        LayoutInflater.from(context).inflate(R.layout.bottom_navigation, this)
        bottom_nav_feed.setOnClickListener {
            setSelected(it)
            _selectedLiveData.value = FEED
        }
        bottom_nav_chatting.setOnClickListener {
            setSelected(it)
            _selectedLiveData.value = CHATTING
        }
        bottom_nav_post.setOnClickListener {
            setSelected(it)
            _selectedLiveData.value = POST
        }
        bottom_nav_alarm.setOnClickListener {
            setSelected(it)
            _selectedLiveData.value = ALARM
        }
        bottom_nav_my_page.setOnClickListener {
            setSelected(it)
            _selectedLiveData.value = MY_PAGE
        }
    }

    private fun setSelected(view: View) {
        listOf(
            bottom_nav_feed,
            bottom_nav_chatting,
            bottom_nav_post,
            bottom_nav_alarm,
            bottom_nav_my_page
        ).forEach {
            it.isSelected = it == view
        }
    }

    enum class BottomNavItem {
        FEED, CHATTING, POST, ALARM, MY_PAGE
    }
}
