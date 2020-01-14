package com.lyh.abroad.presenter.place

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.lyh.abroad.databinding.ItemPlaceSearchBinding
import com.lyh.abroad.presenter.base.listview.BaseViewHolder
import com.lyh.abroad.presenter.model.Country

class CountryItemViewHolder(private val placeViewModel: PlaceViewModel, itemView: View) :
    BaseViewHolder<Country>(itemView), LifecycleOwner {

    val binding: ItemPlaceSearchBinding = ItemPlaceSearchBinding.bind(itemView)
    private val lifecycleRegistry = LifecycleRegistry(this)

    override fun bind(item: Country) {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        binding.apply {
            placeViewModel = this@CountryItemViewHolder.placeViewModel
            lifecycleOwner = this@CountryItemViewHolder
            data = item
        }
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

}
