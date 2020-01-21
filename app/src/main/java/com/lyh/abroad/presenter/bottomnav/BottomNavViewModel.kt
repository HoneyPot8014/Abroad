package com.lyh.abroad.presenter.bottomnav

import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.base.LiveEvent
import com.lyh.abroad.presenter.custom.BottomNavigation

class BottomNavViewModel : BaseViewModel() {

    val currentNav = LiveEvent<BottomNavigation.BottomNavItem>()

}