package com.lyh.abroad.presenter.mapper

import com.lyh.abroad.domain.entity.CalendarEntity
import com.lyh.abroad.presenter.model.CalendarData
import com.lyh.abroad.presenter.model.Date
import java.util.*

object CalendarMapper {

    fun toModel(calendarEntity: CalendarEntity) =
        java.util.Calendar.getInstance(Locale.getDefault()).let { calendar ->
            CalendarData(
                calendarEntity.year,
                calendarEntity.month,
                calendarEntity.days.map {
                    calendar.timeInMillis = it.date
                    Date(it.date, calendar.get(java.util.Calendar.DAY_OF_MONTH))
                }
            )
        }
}
