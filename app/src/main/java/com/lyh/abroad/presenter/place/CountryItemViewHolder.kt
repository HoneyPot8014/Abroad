package com.lyh.abroad.presenter.place

import android.view.View
import androidx.lifecycle.*
import com.lyh.abroad.databinding.ItemPlaceSearchBinding
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.base.listview.BaseViewHolder
import com.lyh.abroad.presenter.model.Country

class CountryItemViewHolder(private val viewModelStore: ViewModelStore, itemView: View) :
    BaseViewHolder<Country>(itemView), LifecycleOwner {

    val binding: ItemPlaceSearchBinding = ItemPlaceSearchBinding.bind(itemView)
    private val lifecycleRegistry = LifecycleRegistry(this)

    override fun bind(item: Country) {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        binding.apply {
            placeViewModel =
                ViewModelProvider(viewModelStore, ViewModelFactory.get()).get(PlaceViewModel::class.java)
            lifecycleOwner = this@CountryItemViewHolder
            data = item
        }
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

}
