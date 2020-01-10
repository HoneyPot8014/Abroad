package com.lyh.abroad.presenter.place

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.domain.entity.CountryEntity
import com.lyh.abroad.presenter.base.listview.BaseAdapter

@BindingAdapter("countryItem")
fun RecyclerView.setList(list: List<CountryEntity>?) {
    if (!list.isNullOrEmpty()) {
        (adapter as? BaseAdapter<CountryEntity, CountryItemViewHolder>)?.submitList(list)
    }
}
