package com.childmathematics.android.shiftschedule.basis.window

enum class WindowMode {
    SINGLE_PORTRAIT,
    SINGLE_LANDSCAPE,
    DUAL_PORTRAIT,
    DUAL_LANDSCAPE;

    val isDualScreen: Boolean get() = this == DUAL_PORTRAIT || this == DUAL_LANDSCAPE
}
