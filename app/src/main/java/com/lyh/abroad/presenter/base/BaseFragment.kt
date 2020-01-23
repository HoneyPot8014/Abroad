package com.lyh.abroad.presenter.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.lyh.abroad.presenter.bottomnav.BottomNavViewModel

open class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    protected val dispatcher by lazy { activity?.onBackPressedDispatcher }
    private val bottomNavViewModel by activityViewModels<BottomNavViewModel> {
        ViewModelFactory.get(requireActivity().application)
    }

    fun showSnackMessage(text: String) {
        Snackbar.make(view!!, text, Snackbar.LENGTH_LONG).show()
    }

    fun showBottomNav() {
        bottomNavViewModel.showBottomNav.value = true
    }

    fun hideBottomNav() {
        bottomNavViewModel.showBottomNav.value = false
    }
}
