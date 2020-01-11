package com.lyh.abroad.presenter.place

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lyh.abroad.R
import com.lyh.abroad.domain.entity.CountryEntity
import com.lyh.abroad.presenter.base.listview.BaseAdapter

class PlaceCountryListAdapter : BaseAdapter<CountryEntity, CountryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder =
        CountryItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_place_search, parent, false)
        )

}