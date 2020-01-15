package com.lyh.abroad.presenter.place.country

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lyh.abroad.R
import com.lyh.abroad.presenter.base.listview.BaseAdapter
import com.lyh.abroad.presenter.model.Country
import com.lyh.abroad.presenter.place.PlaceViewModel

class CountryListAdapter(private val placeViewModel: PlaceViewModel) :
    BaseAdapter<Country, CountryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder =
        CountryItemViewHolder(
            placeViewModel,
            LayoutInflater.from(parent.context).inflate(R.layout.item_place_search, parent, false)
        )
}
