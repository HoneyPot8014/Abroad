package com.lyh.abroad.presenter.place

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.presenter.base.listview.BaseAdapter
import com.lyh.abroad.presenter.model.City
import com.lyh.abroad.presenter.model.Country
import com.lyh.abroad.presenter.place.city.CityItemViewHolder
import com.lyh.abroad.presenter.place.country.CountryItemViewHolder

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
fun RecyclerView.setCityList(list: List<City>?) {
    if (list != null) {
        (adapter as? BaseAdapter<City, CityItemViewHolder>)?.run {
            submitList(list)
            scrollToPosition(0)
        }
    }
}
