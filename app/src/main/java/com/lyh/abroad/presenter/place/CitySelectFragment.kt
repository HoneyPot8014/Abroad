package com.lyh.abroad.presenter.place


import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentCitySelectBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.base.listview.BaseListDivider
import kotlinx.android.synthetic.main.fragment_city_select.*

class CitySelectFragment : BaseFragment(R.layout.fragment_city_select) {

    private val placeViewModel by viewModels<PlaceViewModel>(
        { parentFragment ?: this@CitySelectFragment },
        { ViewModelFactory.get(requireActivity().application) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentCitySelectBinding.bind(view).apply {
            placeViewModel = this@CitySelectFragment.placeViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        dispatcher?.addCallback(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        city_list.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(BaseListDivider(0.5f))
            adapter = PlaceCityListAdapter(placeViewModel)
        }
    }
}
