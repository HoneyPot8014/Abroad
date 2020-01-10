package com.lyh.abroad.presenter.place

import android.view.View
import android.widget.TextView
import com.lyh.abroad.R
import com.lyh.abroad.domain.entity.CountryEntity
import com.lyh.abroad.presenter.base.listview.BaseViewHolder

class CountryItemViewHolder(itemView: View) : BaseViewHolder<CountryEntity>(itemView) {

    override fun bind(item: CountryEntity) {
        val a = item.countryEmoji + " " + item.countryName
        itemView.findViewById<TextView>(R.id.nation_name).text = a
    }
}