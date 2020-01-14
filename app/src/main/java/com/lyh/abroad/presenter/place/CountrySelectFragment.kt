package com.lyh.abroad.presenter.place


import android.os.Bundle
import android.view.View
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
import kotlinx.android.synthetic.main.fragment_country_select.*


class CountrySelectFragment : BaseFragment(R.layout.fragment_country_select) {

    private lateinit var binding: FragmentCountrySelectBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCountrySelectBinding.bind(view).apply {
            placeViewModel = viewModels<PlaceViewModel>(
                { parentFragment ?: this@CountrySelectFragment} ,
                { ViewModelFactory.get(requireActivity().application) }
            ).value
            lifecycleOwner = viewLifecycleOwner
        }

        nation_list.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(BaseListDivider(0.5f))
            adapter = PlaceCountryListAdapter(binding.placeViewModel ?: return@apply)
        }

        place_back_button.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.placeViewModel?.statusLiveData?.observe(viewLifecycleOwner) {
            if (it == Status.Success) parentFragmentManager.popBackStack()
        }
    }
}
