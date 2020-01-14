package com.lyh.abroad.presenter.base

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

open class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    protected val dispatcher by lazy { activity?.onBackPressedDispatcher }

    fun showSnackMessage(text: String) {
        Snackbar.make(view!!, text, Snackbar.LENGTH_LONG).show()
    }
}
