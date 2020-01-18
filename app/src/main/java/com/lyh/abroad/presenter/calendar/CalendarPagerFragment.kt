package com.lyh.abroad.presenter.calendar


import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentCalendarPagerBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_calendar_pager.*

class CalendarPagerFragment : BaseFragment(R.layout.fragment_calendar_pager) {

    private val calendarViewModel by viewModels<CalendarViewModel>(
        { parentFragment ?: this },
        { ViewModelFactory.get(requireActivity().application) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatcher?.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        setUpBinding()
        calendar_pager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = CalendarPagerAdapter(this@CalendarPagerFragment, calendarViewModel)
            calendarViewModel.calendarLiveData.observe(viewLifecycleOwner) {
                (adapter as CalendarPagerAdapter).addCalendar(it)
            }
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    if (currentItem == adapter!!.itemCount - 1) {
                        calendarViewModel.getNextCalendar()
                    }
                }
            })
        }
    }

    private fun setUpBinding() {
        FragmentCalendarPagerBinding.bind(view ?: return).apply {
            lifecycleOwner = viewLifecycleOwner
            calendarViewModel = this@CalendarPagerFragment.calendarViewModel
        }
    }
}
