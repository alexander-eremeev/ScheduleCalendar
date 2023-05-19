package com.childmathematics.android.shiftschedule.basis.uiextension

fun lerp(
    startValue: Float,
    endValue: Float,
    fraction: Float
) = startValue + fraction * (endValue - startValue)
