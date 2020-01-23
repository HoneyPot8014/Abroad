package com.lyh.abroad.presenter.bottomnav

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("showNav")
fun View.showBottomNav(show: Boolean?) {
    if (show != null) {
        isVisible = show
    }
}
