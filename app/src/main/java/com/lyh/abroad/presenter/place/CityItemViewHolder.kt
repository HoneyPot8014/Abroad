package com.lyh.abroad.presenter.place

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.lyh.abroad.databinding.ItemCitySearchBinding
import com.lyh.abroad.presenter.base.listview.BaseViewHolder
import com.lyh.abroad.presenter.model.City

class CityItemViewHolder(private val placeViewModel: PlaceViewModel, itemView: View) :
    BaseViewHolder<City>(itemView), LifecycleOwner {

    val binding: ItemCitySearchBinding = ItemCitySearchBinding.bind(itemView)
    private val lifecycleRegistry = LifecycleRegistry(this)

    override fun bind(item: City) {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        binding.apply {
            placeViewModel = this@CityItemViewHolder.placeViewModel
            lifecycleOwner = this@CityItemViewHolder
            data = item
        }
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

}