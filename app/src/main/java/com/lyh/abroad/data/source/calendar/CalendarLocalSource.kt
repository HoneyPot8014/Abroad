package com.lyh.abroad.data.source.calendar

import com.lyh.abroad.domain.entity.DateEntity
import com.lyh.abroad.domain.model.ResultModel
import java.util.*

object CalendarLocalSource: CalendarSource {

    private val calendar = Calendar.getInstance()

    override suspend fun getCalendar(year: Int, month: Int): ResultModel<List<DateEntity>> {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        return (0..calendar.getActualMaximum(Calendar.DAY_OF_MONTH)).map {
            calendar.set(Calendar.DAY_OF_MONTH, it)
            DateEntity(calendar.timeInMillis)
        }.let {
            ResultModel.onSuccess(it)
        }
    }
}
