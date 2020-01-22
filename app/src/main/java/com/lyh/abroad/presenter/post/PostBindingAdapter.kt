package com.lyh.abroad.presenter.post

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.lyh.abroad.R
import com.lyh.abroad.presenter.model.City
import com.lyh.abroad.presenter.model.Country

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
