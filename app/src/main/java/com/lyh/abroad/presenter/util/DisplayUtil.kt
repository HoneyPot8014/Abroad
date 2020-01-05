package com.lyh.abroad.presenter.util

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

object DisplayUtil {

    fun dpToPx(context: Context, dp: Float): Int =
        context.resources.displayMetrics.let {
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, it).toInt()
        }

    fun pxToDp(context: Context, px: Float): Float =
        context.resources.displayMetrics.let {
            px / (it.densityDpi / DisplayMetrics.DENSITY_DEFAULT.toFloat())
        }
}