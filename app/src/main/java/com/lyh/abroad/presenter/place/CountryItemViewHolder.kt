package com.lyh.abroad.presenter.place

import android.view.View
import androidx.lifecycle.*
import com.lyh.abroad.databinding.ItemPlaceSearchBinding
import com.lyh.abroad.domain.entity.CountryEntity
import com.lyh.abroad.presenter.base.ViewModelFactory
import com.lyh.abroad.presenter.base.listview.BaseViewHolder

class CountryItemViewHolder(private val viewModelStore: ViewModelStore, itemView: View) :
    BaseViewHolder<CountryEntity>(itemView), LifecycleOwner {

    val binding: ItemPlaceSearchBinding = ItemPlaceSearchBinding.bind(itemView)
    private val lifecycleRegistry = LifecycleRegistry(this)

    override fun bind(item: CountryEntity) {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        binding.placeViewModel =
            ViewModelProvider(viewModelStore, ViewModelFactory.get()).get(PlaceViewModel::class.java)
    }

    fun onDestroyed() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

}
