package com.lyh.abroad.presenter.place


import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.R
import com.lyh.abroad.databinding.FragmentPlaceBinding
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.base.listview.BaseListDivider
import kotlinx.android.synthetic.main.fragment_place.*


class PlaceFragment : BaseFragment(R.layout.fragment_place) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentPlaceBinding.bind(view).apply {
            placeViewModel = activityViewModels<PlaceViewModel>(ViewModelFactory::get).value
            lifecycleOwner = viewLifecycleOwner
        }

        nation_list.apply {
            adapter = PlaceCountryListAdapter(activity?.viewModelStore ?: return@apply)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(BaseListDivider(4f))
        }

        place_cancel.setOnClickListener {
            activity?.onBackPressed()
        }

        place_confirm.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}
