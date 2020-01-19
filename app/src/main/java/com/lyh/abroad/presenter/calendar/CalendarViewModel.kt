package com.lyh.abroad.presenter.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lyh.abroad.domain.interactor.date.GetCalendarUsecase
import com.lyh.abroad.presenter.base.BaseViewModel
import com.lyh.abroad.presenter.mapper.CalendarMapper
import com.lyh.abroad.presenter.model.CalendarData
import com.lyh.abroad.presenter.model.Date
import kotlinx.coroutines.launch
import java.util.*

class CalendarViewModel(
    private val getCalendarUsecase: GetCalendarUsecase
) : BaseViewModel() {

    private val calendar = Calendar.getInstance()

    private val _currentCalendar = MutableLiveData<CalendarData>()
    val currentCalendar
        get() = _currentCalendar

    private val _calendarLiveData = MutableLiveData<List<CalendarData>>()
    val calendarLiveData: LiveData<List<CalendarData>>
        get() = _calendarLiveData

    private val _startDateLiveData = MutableLiveData<Date>()
    val startDateLiveData: LiveData<Date>
        get() = _startDateLiveData

    private val _endDateLiveData = MutableLiveData<Date>()
    val endDateLiveData: LiveData<Date>
        get() = _endDateLiveData

    init {
        viewModelScope.launch {
            CalendarMapper.toModel(getCalendarUsecase.execute().data ?: return@launch).also {
                _calendarLiveData.value = _calendarLiveData.value?.plus(it) ?: kotlin.run {
                    listOf(it)
                }
            }
        }
    }

    fun setCurrent(position: Int) {
        calendarLiveData.value?.also { list ->
            _currentCalendar.value = list[position]
            if (list.size - 1 == position) {
                getNext()
            }
        }
    }

    private fun getNext() {
        viewModelScope.launch {
            calendarLiveData.value?.let {
                CalendarMapper.toEntity(it.last()).also {
                    calendar.apply {
                        set(Calendar.YEAR, it.year)
                        set(Calendar.MONTH, it.month)
                        add(Calendar.MONTH, 1)
                    }
                }
                val newCalendar = CalendarMapper.toModel(
                    getCalendarUsecase.execute(
                        GetCalendarUsecase.GetCalendarParam(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH)
                        )
                    ).data ?: return@launch
                )
                _calendarLiveData.value = it.plus(newCalendar)
            }
        }
    }

    fun onDateSelected(date: Date) {
        when {
            startDateLiveData.value == null && endDateLiveData.value == null -> {
                _startDateLiveData.value = date
            }
            startDateLiveData.value == date && endDateLiveData.value == null -> {
                _startDateLiveData.value = null
            }
            startDateLiveData.value == date && endDateLiveData.value != null -> {
                _startDateLiveData.value = null
                _endDateLiveData.value = null
            }
            startDateLiveData.value != null && endDateLiveData.value == null -> {
                // period로 바꿔야함.
                _endDateLiveData.value = date
            }
            startDateLiveData.value != null && endDateLiveData.value == date -> {
                _startDateLiveData.value = null
                _endDateLiveData.value = null
            }
            startDateLiveData.value == null && endDateLiveData.value != null -> {
                _startDateLiveData.value = date
            }
        }
    }
}
