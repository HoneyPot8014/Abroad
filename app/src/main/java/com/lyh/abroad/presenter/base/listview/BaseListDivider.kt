package com.lyh.abroad.presenter.base.listview

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.presenter.util.DisplayUtil

class BaseListDivider(
    private val dp: Float,
    private val color: Int = 0xFFebebeb.toInt()
) : RecyclerView.ItemDecoration() {

    private val paint = Paint().apply {
        this.color = this@BaseListDivider.color
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        parent.children.forEachIndexed { index, view ->
            if (index != parent.childCount - 1) {
                val top =
                    view.bottom + (view.layoutParams as RecyclerView.LayoutParams).bottomMargin
                val bottom = top + DisplayUtil.dpToPx(parent.context, dp)
                c.drawRect(
                    left.toFloat(),
                    top.toFloat(),
                    right.toFloat(),
                    bottom.toFloat(),
                    paint
                )
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.apply {
                top += DisplayUtil.dpToPx(parent.context, dp)
            }
        }
    }
}
