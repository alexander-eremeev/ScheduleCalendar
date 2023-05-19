package com.childmathematics.android.shiftschedule.aspects.theme.ui

import android.os.Build
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.childmathematics.android.basement.core.viewmodel.StatefulViewModel
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.aspects.theme.data.IThemeEnvironment
import com.childmathematics.android.shiftschedule.basis.extension.select
import com.childmathematics.android.shiftschedule.basis.theme.AuroraItemBackgroundL2
import com.childmathematics.android.shiftschedule.basis.theme.AuroraPrimary
import com.childmathematics.android.shiftschedule.basis.theme.LightPrimary
import com.childmathematics.android.shiftschedule.basis.theme.NightItemBackgroundL2
import com.childmathematics.android.shiftschedule.basis.theme.NightPrimary
import com.childmathematics.android.shiftschedule.basis.theme.SunriseItemBackgroundL2
import com.childmathematics.android.shiftschedule.basis.theme.SunrisePrimary
import com.childmathematics.android.shiftschedule.basis.theme.TwilightItemBackgroundL1
import com.childmathematics.android.shiftschedule.basis.theme.TwilightPrimary
import com.childmathematics.android.shiftschedule.model.Theme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    themeEnvironment: IThemeEnvironment,
) :
    StatefulViewModel<ThemeState, Unit, ThemeAction, IThemeEnvironment>(ThemeState(), themeEnvironment) {

    init {
        initTheme()
    }

    override fun dispatch(action: ThemeAction) {
        when (action) {
            is ThemeAction.SelectTheme -> applyTheme(action.selected)
        }
    }

    private fun initTheme() {
        viewModelScope.launch {
            setState { copy(items = initial()) }

            environment.getTheme()
                .collect {
                    setState { copy(items = items.select(it)) }
                }
        }
    }

    private fun applyTheme(item: ThemeItem) {
        viewModelScope.launch {
            environment.setTheme(item.theme)
        }
    }

    private fun initial(): List<ThemeItem> {
        val data = mutableListOf<ThemeItem>()

        data.add(
            ThemeItem(
                R.string.setting_theme_automatic,
                Theme.SYSTEM,
                Brush.linearGradient(
                    colors = listOf(
                        Color.White,
                        NightItemBackgroundL2
                    )
                ),
                false
            )
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            data.add(
                ThemeItem(
                    R.string.setting_theme_wallpaper,
                    Theme.WALLPAPER,
                    Brush.linearGradient(
                        colors = listOf()
                    ),
                    false
                )
            )
        }

        data.add(
            ThemeItem(
                R.string.setting_theme_light,
                Theme.LIGHT,
                Brush.linearGradient(
                    colors = listOf(
                        LightPrimary,
                        Color.White
                    )
                ),
                false
            )
        )

        data.add(
            ThemeItem(
                R.string.setting_theme_twilight,
                Theme.TWILIGHT,
                Brush.linearGradient(
                    colors = listOf(
                        TwilightPrimary,
                        TwilightItemBackgroundL1
                    )
                ),
                false
            )
        )

        data.add(
            ThemeItem(
                R.string.setting_theme_night,
                Theme.NIGHT,
                Brush.linearGradient(
                    colors = listOf(
                        NightPrimary,
                        NightItemBackgroundL2
                    )
                ),
                false
            )
        )

        data.add(
            ThemeItem(
                R.string.setting_theme_sunrise,
                Theme.SUNRISE,
                Brush.linearGradient(
                    colors = listOf(
                        SunrisePrimary,
                        SunriseItemBackgroundL2
                    )
                ),
                false
            )
        )

        data.add(
            ThemeItem(
                R.string.setting_theme_aurora,
                Theme.AURORA,
                Brush.linearGradient(
                    colors = listOf(
                        AuroraPrimary,
                        AuroraItemBackgroundL2
                    )
                ),
                false
            )
        )

        return data
    }

}