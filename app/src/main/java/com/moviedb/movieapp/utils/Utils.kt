package com.moviedb.movieapp.utils

import android.content.Context
import android.util.TypedValue

object Utils {

    const val KEY_SORT = "sortingKey"

    fun dpToPx(c: Context, dp: Int): Int {
        val r = c.resources
        return Math.round(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                r.displayMetrics
            )
        )
    }
}