package com.lyh.abroad.presenter.place

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.lyh.abroad.databinding.ItemCitySearchBinding
import com.lyh.abroad.domain.entity.CityEntity
import com.lyh.abroad.presenter.base.listview.BaseViewHolder

class CityItemViewHolder(private val placeViewModel: PlaceViewModel, itemView: View) :
    BaseViewHolder<CityEntity>(itemView), LifecycleOwner {

    val binding: ItemCitySearchBinding = ItemCitySearchBinding.bind(itemView)
    private val lifecycleRegistry = LifecycleRegistry(this)

    override fun bind(item: CityEntity) {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        binding.apply {
            placeViewModel = this@CityItemViewHolder.placeViewModel
            lifecycleOwner = this@CityItemViewHolder
            data = item
        }
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

}