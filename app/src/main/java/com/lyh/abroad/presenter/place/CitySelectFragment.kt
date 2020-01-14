package com.lyh.abroad.presenter.place


import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import com.lyh.abroad.R
import com.lyh.abroad.presenter.base.BaseFragment

class CitySelectFragment : BaseFragment(R.layout.fragment_city_select) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatcher?.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
    }
}
