package com.lyh.abroad.presenter.place

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.domain.entity.CityEntity
import com.lyh.abroad.presenter.base.listview.BaseAdapter
import com.lyh.abroad.presenter.model.Country

@BindingAdapter("countryItem")
fun RecyclerView.setList(list: List<Country>?) {
    if (list != null) {
        (adapter as? BaseAdapter<Country, CountryItemViewHolder>)?.run {
            submitList(list)
            scrollToPosition(0)
        }
    }
}

@BindingAdapter("cityItem")
fun RecyclerView.setCityList(list: List<CityEntity>?) {
    if (list != null) {
        (adapter as? BaseAdapter<CityEntity, CityItemViewHolder>)?.run {
            submitList(list)
            scrollToPosition(0)
        }
    }
}
