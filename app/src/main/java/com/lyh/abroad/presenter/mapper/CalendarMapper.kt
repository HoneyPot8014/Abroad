package com.lyh.abroad.presenter.mapper

import com.lyh.abroad.domain.entity.CalendarEntity
import com.lyh.abroad.domain.entity.DateEntity
import com.lyh.abroad.presenter.model.CalendarData
import com.lyh.abroad.presenter.model.Date
import java.text.SimpleDateFormat
import java.util.*

object CalendarMapper {

    fun toModel(calendarEntity: CalendarEntity) =
        Calendar.getInstance(Locale.getDefault()).let { calendar ->
            CalendarData(
                calendarEntity.year,
                calendarEntity.month + 1,
                calendarEntity.days.map {
                    calendar.timeInMillis = it.date
                    val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
                    Date(
                        it.date,
                        format.format(Date(it.date)),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.WEEK_OF_MONTH),
                        calendar.get(Calendar.DAY_OF_WEEK)
                    )
                }
            )
        }

    fun toEntity(calendarData: CalendarData) =
        CalendarEntity(
            calendarData.year,
            calendarData.month - 1,
            calendarData.date.map {
                DateEntity(it.date)
            }
        )
}
