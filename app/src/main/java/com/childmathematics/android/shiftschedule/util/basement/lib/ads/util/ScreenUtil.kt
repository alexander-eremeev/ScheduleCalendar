package com.childmathematics.android.basement.lib.ads.util

import android.content.Context
import kotlin.math.roundToInt

object ScreenUtil {

    val Context.screenWidth: Int
        get() = resources.displayMetrics.run { widthPixels / density }.roundToInt()

    val Context.screenHeight: Int
        get() = resources.displayMetrics.run { heightPixels / density }.roundToInt()
}
