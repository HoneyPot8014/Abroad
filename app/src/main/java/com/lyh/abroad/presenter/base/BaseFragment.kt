package com.lyh.dailydialog.presenter.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

open class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    fun <T : ViewModel> getViewModel(modelClass: Class<T>): T? {
        activity?.let {
            if (getScopedViewModelClasses<ViewModel>().contains(modelClass)) {
                return newViewModel(modelClass)
            }
            val parentFragment = parentFragment as? BaseFragment
            if (parentFragment != null) {
                return parentFragment.getViewModel(modelClass)
            }
            return (activity as BaseActivity).getViewModel(modelClass)
        } ?: return null
    }

    protected fun <T : ViewModel> getScopedViewModelClasses(): List<Class<out T>> = emptyList()

    private fun <T : ViewModel> newViewModel(modelClass: Class<out T>): T =
        ViewModelProviders.of(this).get(modelClass)

}
