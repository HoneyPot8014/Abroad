package com.lyh.abroad.data.repository

import com.lyh.abroad.data.source.calendar.CalendarSource
import com.lyh.abroad.domain.entity.DateEntity
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.CalendarRepository

class CalendarRepositoryImpl private constructor(
    private val calendarSource: CalendarSource
) : CalendarRepository {

    private val cache = mutableMapOf<CacheKey, List<DateEntity>>()

    companion object {

        var INSTANCE: CalendarRepositoryImpl? = null

        fun getInstance(calendarSource: CalendarSource) =
            INSTANCE ?: CalendarRepositoryImpl(calendarSource).also {
                INSTANCE = it
            }

    }

    override suspend fun getCalendar(year: Int, month: Int): ResultModel<List<DateEntity>> =
        CacheKey(year, month).let { key ->
            cache[key]?.let {
                ResultModel.onSuccess(it)
            } ?: kotlin.run {
                calendarSource.getCalendar(year, month).also {
                    cache[key] = it.data ?: return@also
                }
            }
        }

    override suspend fun setCalendar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    data class CacheKey(val year: Int, val month: Int)

}
