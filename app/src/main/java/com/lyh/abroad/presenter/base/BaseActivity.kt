package com.lyh.dailydialog.presenter.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity : AppCompatActivity() {

    fun <T : ViewModel> getViewModel(modelClass: Class<out T>): T =
        ViewModelProviders.of(this)[modelClass]

    fun <T : BaseFragment> findFragment(modelClass: Class<T>): T? =
        supportFragmentManager.primaryNavigationFragment
            ?.childFragmentManager
            ?.fragments
            ?.find {
                it.tag == modelClass::class.qualifiedName
            } as? T
}
