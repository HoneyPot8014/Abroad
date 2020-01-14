package com.lyh.abroad.presenter.post


import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentPostBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.place.CountrySelectFragment
import com.lyh.abroad.presenter.place.PlaceViewModel
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : BaseFragment(R.layout.fragment_post) {

    private val placeViewModel by activityViewModels<PlaceViewModel> {
        ViewModelFactory.get(requireActivity().application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentPostBinding.bind(view).apply {
            placeViewModel = this@PostFragment.placeViewModel
        }

        post_back_button.setOnClickListener {
            activity?.onBackPressed()
        }

        post_select_country.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CountrySelectFragment())
                .addToBackStack(null)
                .commit()
        }
    }


}
