package com.lyh.abroad.presenter.calendar


import android.os.Bundle
import android.view.View
import com.lyh.abroad.R
import com.lyh.abroad.presenter.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment private constructor(

) : BaseFragment(R.layout.fragment_calendar) {

    companion object {

        fun newInstance(currentPage: Int) =
            CalendarFragment().apply {
                arguments = Bundle().apply {
                    putInt("currentPage", currentPage)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tt.text = arguments?.getInt("currentPage")?.toString()
    }
}
