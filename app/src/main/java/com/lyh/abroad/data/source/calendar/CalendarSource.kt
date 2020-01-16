package com.lyh.abroad.data.source.calendar

import com.lyh.abroad.domain.entity.DateEntity
import com.lyh.abroad.domain.model.ResultModel

interface CalendarSource {

    suspend fun getCalendar(year: Int, month: Int): ResultModel<List<DateEntity>>

}