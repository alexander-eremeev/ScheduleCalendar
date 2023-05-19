package com.childmathematics.android.shiftschedule.aspects.theme.ui

sealed class ThemeAction {
    data class SelectTheme(val selected: ThemeItem) : ThemeAction()
}
