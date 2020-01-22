package com.lyh.abroad.presenter.calendar


import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentCalendarPagerBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.BaseViewModel.Status.*
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.bottomnav.BottomNavViewModel
import com.lyh.abroad.presenter.custom.BottomNavigation
import com.lyh.abroad.presenter.place.PlaceViewModel
import com.lyh.abroad.presenter.post.PostViewModel
import kotlinx.android.synthetic.main.fragment_calendar_pager.*

class CalendarPagerFragment : BaseFragment(R.layout.fragment_calendar_pager) {

    private val calendarViewModel by viewModels<CalendarViewModel>(
        { parentFragment ?: this },
        { ViewModelFactory.get(requireActivity().application) }
    )
    private val postViewModel by viewModels<PostViewModel>(
        { parentFragment ?: this },
        { ViewModelFactory.get(requireActivity().application) }
    )
    private val placeViewModel by viewModels<PlaceViewModel>(
        { parentFragment ?: this },
        { ViewModelFactory.get(requireActivity().application) }
    )
    private val bottomNavViewModel by activityViewModels<BottomNavViewModel> {
        ViewModelFactory.get(requireActivity().application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatcher?.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        setUpBinding()
        // TODO : 로직 bindingAdapter로 변경
        calendar_pager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = CalendarPagerAdapter(this@CalendarPagerFragment, calendarViewModel)
            calendarViewModel.calendarLiveData.observe(viewLifecycleOwner) {
                (adapter as CalendarPagerAdapter).addCalendar(it)
            }
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    calendarViewModel.setCurrent(position)
                }
            })
        }

        postViewModel.statusLiveData.observe(viewLifecycleOwner) {
            when (it) {
                null -> return@observe
                Success -> {
                    hidePg()
                    parentFragmentManager.popBackStack()
                    bottomNavViewModel.currentNav.value = BottomNavigation.BottomNavItem.FEED
                }
                Loading -> showPg()
                is Failed -> {
                    hidePg()
                    showSnackMessage(resources.getString(it.reason.message))
                }
            }
        }
    }

    private fun setUpBinding() {
        FragmentCalendarPagerBinding.bind(view ?: return).apply {
            lifecycleOwner = viewLifecycleOwner
            calendarViewModel = this@CalendarPagerFragment.calendarViewModel
            postViewModel = this@CalendarPagerFragment.postViewModel
            placeViewModel = this@CalendarPagerFragment.placeViewModel
        }
    }

    private fun showPg() {
        if (pg?.visibility == View.INVISIBLE) {
            pg?.visibility = View.VISIBLE
        }
    }

    private fun hidePg() {
        if (pg?.visibility == View.VISIBLE) {
            pg?.visibility = View.INVISIBLE
        }
    }
}
