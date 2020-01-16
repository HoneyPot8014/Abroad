package com.lyh.abroad.domain.repository

import com.lyh.abroad.domain.entity.CalendarEntity
import com.lyh.abroad.domain.model.ResultModel

interface CalendarRepository {

    suspend fun getCalendar(year: Int, month: Int): ResultModel<CalendarEntity>

    suspend fun setCalendar()
}
