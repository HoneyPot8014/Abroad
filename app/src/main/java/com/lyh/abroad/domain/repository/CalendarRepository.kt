package com.lyh.abroad.domain.repository

import com.lyh.abroad.domain.entity.DateEntity
import com.lyh.abroad.domain.model.ResultModel

interface CalendarRepository {

    suspend fun getCalendar(year: Int, month: Int): ResultModel<List<DateEntity>>

    suspend fun setCalendar()
}
