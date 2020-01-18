package com.lyh.abroad.presenter.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.lyh.abroad.databinding.ItemCalendarBinding
import com.lyh.abroad.presenter.calendar.CalendarViewModel
import com.lyh.abroad.presenter.model.Date

class CustomCalendarView(
    context: Context?,
    attrs: AttributeSet?
) : TableLayout(context, attrs), LifecycleOwner {

    var calendarViewModel: CalendarViewModel? = null
    private var date: List<Date>? = null
    private val lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    private fun create() {
        date?.filter { it.weeksOfMonth == 1 }?.let { createRow(it) }
        date?.filter { it.weeksOfMonth == 2 }?.let { createRow(it) }
        date?.filter { it.weeksOfMonth == 3 }?.let { createRow(it) }
        date?.filter { it.weeksOfMonth == 4 }?.let { createRow(it) }
        date?.filter { it.weeksOfMonth == 5 }?.let { createRow(it) }
    }

    private fun createRow(dayList: List<Date>) {
        TableRow(context).apply {
            layoutParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    weight = 1f
                }
            weightSum = 7.0f
            val firstDayOfWeek = dayList.first().daysOfWeek - 1
            (0 until firstDayOfWeek).forEach { _ ->
                addView(createEmptyView())
            }
            dayList.forEach {
                addView(createDay(it))
            }
            (firstDayOfWeek until 7).forEach { _ ->
                addView(createEmptyView())
            }
        }.let {
            addView(it)
        }
    }

    private fun createEmptyView(): View =
        TextView(context).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.MATCH_PARENT
            ).apply {
                weight = 1f
            }
        }

    private fun createDay(date: Date): View =
        ItemCalendarBinding.inflate(LayoutInflater.from(context)).apply {
            lifecycleOwner = this@CustomCalendarView
            calendarViewModel = this@CustomCalendarView.calendarViewModel
            this.date = date
            root.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.MATCH_PARENT
            ).apply {
                weight = 1f
            }
        }.run {
            root
        }

    fun setDate(date: List<Date>) {
        this.date = date
        removeAllViews()
        create()
        requestLayout()
    }
}
