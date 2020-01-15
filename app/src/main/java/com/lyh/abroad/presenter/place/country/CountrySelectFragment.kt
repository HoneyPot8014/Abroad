package com.lyh.abroad.presenter.place.country


import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentCountrySelectBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.BaseViewModel.Status
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.base.listview.BaseListDivider
import com.lyh.abroad.presenter.place.PlaceViewModel
import com.lyh.abroad.presenter.place.city.CitySelectFragment
import com.lyh.abroad.presenter.user.UserViewModel
import kotlinx.android.synthetic.main.fragment_country_select.*


class CountrySelectFragment : BaseFragment(R.layout.fragment_country_select) {

    private val placeViewModel by viewModels<PlaceViewModel>(
        { parentFragment ?: this@CountrySelectFragment },
        { ViewModelFactory.get(requireActivity().application) }
    )
    private val userViewModel by activityViewModels<UserViewModel> {
        ViewModelFactory.get(requireActivity().application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatcher?.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        setUpBinding()
        nation_list.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(BaseListDivider(0.5f))
            adapter =
                CountryListAdapter(
                    placeViewModel
                )
        }

        place_back_button.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        placeViewModel.statusLiveData.observe(viewLifecycleOwner) {
            if (it == Status.Success) {
                if (userViewModel.userLiveData.value == null) {
                    parentFragmentManager.popBackStack()
                } else {
                    parentFragmentManager.commit {
                        replace(R.id.post_container, CitySelectFragment())
                        addToBackStack(null)
                    }
                }
            }
        }

        country_next.setOnClickListener {
            if (placeViewModel.countryLiveData.value != null) {
                parentFragmentManager.commit {
                    replace(R.id.post_container, CitySelectFragment())
                    addToBackStack(null)
                }
            }
        }
    }

    private fun setUpBinding() {
        FragmentCountrySelectBinding.bind(view ?: return).apply {
            placeViewModel = this@CountrySelectFragment.placeViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }
}
