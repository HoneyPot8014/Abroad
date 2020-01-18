package com.lyh.abroad.presenter.calendar

import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.domain.interactor.date.GetCalendarUsecase
import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.mapper.CalendarMapper
import com.lyh.abroad.presenter.model.CalendarData
import kotlinx.coroutines.launch
import java.util.*

class CalendarViewModel(
    private val getCalendarUsecase: GetCalendarUsecase
) : BaseViewModel() {

    private val calendar = Calendar.getInstance()
    private val _currentCalendar = MutableLiveData<CalendarData>()

    private val currentCalendar
        get() = _currentCalendar

    private val _calendarLiveData = MutableLiveData<List<CalendarData>>()
    val calendarLiveData
        get() = _calendarLiveData

    init {
        getNextCalendar()
    }

    fun getNextCalendar() {
        viewModelScope.launch {
            _calendarLiveData.value?.let {
                calendar.apply {
                    set(Calendar.YEAR, it.last().year)
                    set(Calendar.MONTH, it.last().month)
                    add(Calendar.MONTH, 1)
                }
                it.plus(
                    CalendarMapper.toModel(
                        getCalendarUsecase.execute(
                            GetCalendarUsecase.GetCalendarParam(
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH)
                            )
                        ).data ?: return@let
                    )
                ).let { result ->
                    _calendarLiveData.value = result
                }
            } ?: kotlin.run {
                _calendarLiveData.value =
                    listOf(CalendarMapper.toModel(getCalendarUsecase.execute().data ?: return@run))
            }
        }
    }
}
