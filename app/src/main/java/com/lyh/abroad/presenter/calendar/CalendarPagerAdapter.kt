package com.lyh.abroad.presenter.calendar

import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lyh.abroad.presenter.model.CalendarData

class CalendarPagerAdapter(
    parentFragment: Fragment,
    calendarViewModel: CalendarViewModel
) : FragmentStateAdapter(parentFragment.childFragmentManager, parentFragment.lifecycle) {

    private var list = emptyList<CalendarData>()

    init {
        calendarViewModel.calendarLiveData.observe(parentFragment.viewLifecycleOwner) {
            addCalendar(it ?: return@observe)
        }
    }

    override fun createFragment(position: Int): CalendarFragment =
        CalendarFragment.newInstance(position)

    override fun getItemCount(): Int {
        return list.size
    }

    fun addCalendar(list: List<CalendarData>) {
        this.list = list
        notifyDataSetChanged()
    }
}
