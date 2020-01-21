package com.lyh.abroad.presenter.post


import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentPostBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.calendar.CalendarPagerFragment
import com.lyh.abroad.presenter.place.PlaceViewModel
import com.lyh.abroad.presenter.place.country.CountrySelectFragment
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : BaseFragment(R.layout.fragment_post) {

    private val placeViewModel by viewModels<PlaceViewModel>(
        { parentFragment ?: this },
        { ViewModelFactory.get(requireActivity().application) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBinding()
        post_back_button.setOnClickListener {
            activity?.onBackPressed()
        }

        post_select_country.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.post_container,
                    CountrySelectFragment()
                )
                .addToBackStack(null)
                .commit()
        }

        post_next.setOnClickListener {
            parentFragmentManager.commit {
                add(R.id.post_container, CalendarPagerFragment())
                addToBackStack(null)
            }
        }
    }

    private fun setUpBinding() {
        FragmentPostBinding.bind(view ?: return).apply {
            placeViewModel = this@PostFragment.placeViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }
}
