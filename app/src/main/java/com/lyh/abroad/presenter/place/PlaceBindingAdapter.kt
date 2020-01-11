package com.lyh.abroad.presenter.place

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.presenter.base.listview.BaseAdapter
import com.lyh.abroad.presenter.model.Country

@BindingAdapter("countryItem")
fun RecyclerView.setList(list: List<Country>?) {
    if (!list.isNullOrEmpty()) {
        (adapter as? BaseAdapter<Country, CountryItemViewHolder>)?.run {
            submitList(list)
            scrollToPosition(0)
        }
    }
}
