package com.lyh.abroad.presenter.post


import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentPostBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.BaseViewModel.Status.Failed
import com.lyh.abroad.presenter.base.BaseViewModel.Status.Success
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.calendar.CalendarPagerFragment
import com.lyh.abroad.presenter.place.PlaceViewModel
import com.lyh.abroad.presenter.place.country.CountrySelectFragment
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : BaseFragment(R.layout.fragment_post) {

    private val postViewModel by viewModels<PostViewModel>(
        { parentFragment ?: this },
        { ViewModelFactory.get(requireActivity().application) }
    )
    private val placeViewModel by viewModels<PlaceViewModel>(
        { parentFragment ?: this },
        { ViewModelFactory.get(requireActivity().application) }
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBinding()
        hideBottomNav()
        post_back_button.setOnClickListener {
            activity?.onBackPressed()
        }

        post_select_country.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.post_container, CountrySelectFragment())
                addToBackStack(null)
            }
        }

        post_next.setOnClickListener {
            if (placeViewModel.isPlaceSelected.value == true) {
                parentFragmentManager.commit {
                    replace(R.id.post_container, CalendarPagerFragment())
                    addToBackStack(null)
                }
            } else {
                showSnackMessage(
                    context?.getString(R.string.not_selected_place) ?: return@setOnClickListener
                )
            }
        }
        postViewModel.statusLiveData.observe(viewLifecycleOwner) {
            when (it) {
                null -> return@observe
                Success -> {
                    parentFragmentManager.commit {
                        replace(R.id.post_container, CalendarPagerFragment())
                        addToBackStack(null)
                    }
                }
                is Failed -> showSnackMessage(resources.getString(it.reason.message))
            }
        }
    }

    private fun setUpBinding() {
        FragmentPostBinding.bind(view ?: return).apply {
            placeViewModel = this@PostFragment.placeViewModel
            postViewModel = this@PostFragment.postViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }
}
