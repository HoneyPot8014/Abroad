package com.lyh.abroad.presenter.calendar


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentCalendarBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : BaseFragment(R.layout.fragment_calendar) {

    private val calendarViewModel by viewModels<CalendarViewModel>(
        { parentFragment?.parentFragment ?: this },
        { ViewModelFactory.get(requireActivity().application) }
    )

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
        setUpBinding()
        hideBottomNav()
        //TODO 테스트코드 삭제
        val a = arguments?.getInt("currentPage") ?: 0
        calendarViewModel.calendarLiveData.value?.getOrNull(a)?.date.also {
            calendar.setDate(it ?: return@also)
        }
    }

    private fun setUpBinding() {
        FragmentCalendarBinding.bind(view ?: return).apply {
            lifecycleOwner = viewLifecycleOwner
            calendarViewModel = this@CalendarFragment.calendarViewModel
        }
        calendar.calendarViewModel = this.calendarViewModel
    }
}
