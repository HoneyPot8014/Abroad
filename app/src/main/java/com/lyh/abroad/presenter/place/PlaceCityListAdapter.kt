package com.lyh.abroad.presenter.place

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lyh.abroad.R
import com.lyh.abroad.domain.entity.CityEntity
import com.lyh.abroad.presenter.base.listview.BaseAdapter

class PlaceCityListAdapter(private val placeViewModel: PlaceViewModel) :
    BaseAdapter<CityEntity, CityItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityItemViewHolder =
        CityItemViewHolder(
            placeViewModel,
            LayoutInflater.from(parent.context).inflate(R.layout.item_city_search, parent, false)
        )
}