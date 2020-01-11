package com.lyh.abroad.presenter.place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelStore
import com.lyh.abroad.R
import com.lyh.abroad.domain.entity.CountryEntity
import com.lyh.abroad.presenter.base.listview.BaseAdapter

class PlaceCountryListAdapter(private val viewModelStore: ViewModelStore) :
    BaseAdapter<CountryEntity, CountryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder =
        CountryItemViewHolder(
            viewModelStore,
            LayoutInflater.from(parent.context).inflate(R.layout.item_place_search, parent, false)
        )

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.apply {
            data = getItem(position)
        }
    }

    override fun onViewDetachedFromWindow(holder: CountryItemViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onDestroyed()
    }

}
