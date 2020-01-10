package com.lyh.abroad.presenter.base.listview

import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.lyh.abroad.presenter.util.DisplayUtil

class BaseListDivider(
    private val dp: Float,
    private val color: Int = 0xFFebebeb.toInt()
) : RecyclerView.ItemDecoration() {

    private val canvas = Canvas()
    private val paint = Paint()

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        parent.children.forEachIndexed { index, view ->
            if (index != parent.childCount) {
                val top =
                    view.bottom + (view.layoutParams as RecyclerView.LayoutParams).bottomMargin
                val bottom = top + DisplayUtil.dpToPx(parent.context, dp) + top

                canvas.drawRect(
                    left.toFloat(),
                    top.toFloat(),
                    right.toFloat(),
                    bottom.toFloat(),
                    paint.apply {
                        this.color = this@BaseListDivider.color
                    }
                )
            }
        }
    }
}
