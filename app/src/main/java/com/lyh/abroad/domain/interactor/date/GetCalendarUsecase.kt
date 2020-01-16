package com.lyh.abroad.domain.interactor.date

import com.lyh.abroad.domain.entity.DateEntity
import com.lyh.abroad.domain.interactor.BaseUsecase
import com.lyh.abroad.domain.model.ResultModel
import com.lyh.abroad.domain.repository.CalendarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class GetCalendarUsecase(
    private val calendarRepository: CalendarRepository
): BaseUsecase<List<DateEntity>, GetCalendarUsecase.GetCalendarParam>() {

    data class GetCalendarParam(val year: Int, val month: Int): Param()

    override suspend fun bindUsecase(param: GetCalendarParam?): ResultModel<List<DateEntity>> =
        param?.let {
            withContext(Dispatchers.Default) {
                calendarRepository.getCalendar(param.year, param.month)
            }
        } ?: kotlin.run {
            Calendar.getInstance().run {
                calendarRepository.getCalendar(get(Calendar.YEAR), get(Calendar.MONTH))
            }
        }
}
