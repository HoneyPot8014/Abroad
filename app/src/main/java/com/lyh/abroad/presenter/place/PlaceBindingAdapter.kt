package com.lyh.abroad.presenter.place

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.R
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

@BindingAdapter(value = ["country", "city"], requireAll = true)
fun TextView.setPostTitle(country: Country?, city: City?) {
    when {
        country == null && city == null -> {
            setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_drop_down, 0, 0, 0)
            text = context.getString(R.string.select_place)
        }
        country != null && city == null -> {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
            text = country.countryName.plus(" / ").plus("도시를 선택해주세요")
        }
        country != null && city != null -> {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
            text = country.countryName.plus(" / ").plus(city.cityName)
        }
    }
}
