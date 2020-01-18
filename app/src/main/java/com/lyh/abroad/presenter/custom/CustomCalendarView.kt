package com.lyh.abroad.presenter.custom

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.lyh.abroad.presenter.model.Date

class CustomCalendarView(
    context: Context?,
    attrs: AttributeSet?
) : TableLayout(context, attrs) {

    private var date: List<Date>? = null

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
            (0 until dayList.first().daysOfWeek - 1).forEach { i ->
                addView(createDay())
            }
            dayList.forEach {
                addView(createDay(it.displayDay.toString()))
            }
        }.let {
            addView(it)
        }
    }

    private fun createDay(text: String? = null): TextView =
        TextView(context).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 1f
            }
            gravity = Gravity.CENTER
            this.text = text
        }

    fun setDate(date: List<Date>) {
        this.date = date
        create()
        requestLayout()
    }
}
