package com.lyh.abroad.presenter.main

import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.custom.BottomNavigation

class BottomNavViewModel : BaseViewModel() {

    val currentNav = MutableLiveData<BottomNavigation.BottomNavItem>()

}