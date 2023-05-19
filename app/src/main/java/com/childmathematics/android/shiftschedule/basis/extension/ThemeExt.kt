package com.childmathematics.android.shiftschedule.basis.extension

import com.childmathematics.android.shiftschedule.aspects.theme.ui.ThemeItem
import com.childmathematics.android.shiftschedule.basis.datasource.preference.model.ThemePreference
import com.childmathematics.android.shiftschedule.model.Theme

fun Theme.toThemePreference() = when (this) {
    Theme.SYSTEM -> ThemePreference.SYSTEM
    Theme.LIGHT -> ThemePreference.LIGHT
    Theme.TWILIGHT -> ThemePreference.TWILIGHT
    Theme.NIGHT -> ThemePreference.NIGHT
    Theme.SUNRISE -> ThemePreference.SUNRISE
    Theme.AURORA -> ThemePreference.AURORA
    Theme.WALLPAPER -> ThemePreference.WALLPAPER
}

fun ThemePreference.toTheme() = when (this) {
    ThemePreference.SYSTEM -> Theme.SYSTEM
    ThemePreference.LIGHT -> Theme.LIGHT
    ThemePreference.TWILIGHT -> Theme.TWILIGHT
    ThemePreference.NIGHT -> Theme.NIGHT
    ThemePreference.SUNRISE -> Theme.SUNRISE
    ThemePreference.AURORA -> Theme.AURORA
    ThemePreference.WALLPAPER -> Theme.WALLPAPER
}

fun List<ThemeItem>.select(theme: Theme): List<ThemeItem> {
    return map {
        it.copy(applied = it.theme == theme)
    }
}
