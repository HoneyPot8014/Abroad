package com.lyh.abroad.data.source.calendar

import com.lyh.abroad.domain.entity.CalendarEntity
import com.lyh.abroad.domain.entity.DateEntity
import com.lyh.abroad.domain.model.ResultModel
import java.util.*

object CalendarLocalSource : CalendarSource {

    override suspend fun getCalendar(year: Int, month: Int): ResultModel<CalendarEntity> {
        val calendar = Calendar.getInstance(Locale.getDefault()).apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
        }
        return (1..calendar.getActualMaximum(Calendar.DAY_OF_MONTH)).map {
            calendar.set(Calendar.DAY_OF_MONTH, it)
            DateEntity(calendar.timeInMillis)
        }.let {
            ResultModel.onSuccess(
                CalendarEntity(year, month, it)
            )
        }
    }
}
