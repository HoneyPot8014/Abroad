package com.lyh.abroad.presenter.place

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lyh.abroad.R
import com.lyh.abroad.presenter.base.listview.BaseAdapter
import com.lyh.abroad.presenter.model.City

class PlaceCityListAdapter(private val placeViewModel: PlaceViewModel) :
    BaseAdapter<City, CityItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityItemViewHolder =
        CityItemViewHolder(
            placeViewModel,
            LayoutInflater.from(parent.context).inflate(R.layout.item_city_search, parent, false)
        )
}
