package com.lyh.abroad.presenter.place.city


import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentCitySelectBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.base.listview.BaseListDivider
import com.lyh.abroad.presenter.place.PlaceViewModel
import kotlinx.android.synthetic.main.fragment_city_select.*

class CitySelectFragment : BaseFragment(R.layout.fragment_city_select) {

    private val placeViewModel by viewModels<PlaceViewModel>(
        { parentFragment ?: this@CitySelectFragment },
        { ViewModelFactory.get(requireActivity().application) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dispatcher?.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        hideBottomNav()
        setUpBinding()
        city_back_button.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        city_list.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(BaseListDivider(0.5f))
            adapter = CityListAdapter(
                placeViewModel
            )
        }
        placeViewModel.cityLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                parentFragmentManager.popBackStack()
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun setUpBinding() {
        FragmentCitySelectBinding.bind(view ?: return).apply {
            placeViewModel = this@CitySelectFragment.placeViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }
}
