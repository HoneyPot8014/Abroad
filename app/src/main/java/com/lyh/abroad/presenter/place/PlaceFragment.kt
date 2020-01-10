package com.lyh.abroad.presenter.place


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.R
import com.lyh.abroad.domain.entity.CountryEntity
import com.lyh.abroad.domain.interactor.place.GetCountryUsecase
import com.lyh.abroad.presenter.base.BaseFragment
import com.lyh.abroad.presenter.base.listview.BaseAdapter
import com.lyh.abroad.presenter.base.listview.BaseListDivider
import kotlinx.android.synthetic.main.fragment_place.*
import kotlinx.coroutines.launch


class PlaceFragment : BaseFragment(R.layout.fragment_place) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        place_list.apply {
            adapter = object : BaseAdapter<CountryEntity, CountryItemViewHolder>() {
                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): CountryItemViewHolder =
                    CountryItemViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_place_search, parent, false)
                    )
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(BaseListDivider(4f))
        }

        val usecase = GetCountryUsecase()
        lifecycleScope.launch {
            val list = usecase.execute().data
            (place_list.adapter as BaseAdapter<CountryEntity, CountryItemViewHolder>).submitList(list)
        }

        place_search?.addTextChangedListener {
            lifecycleScope.launch {
                val list = usecase.execute(GetCountryUsecase.CountryParam(it.toString())).data
                (place_list.adapter as BaseAdapter<CountryEntity, CountryItemViewHolder>).submitList(list)
            }
        }
    }
}
