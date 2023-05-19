package com.childmathematics.android.shiftschedule.aspects.theme.data

import com.childmathematics.android.shiftschedule.model.Theme
import kotlinx.coroutines.flow.Flow

interface IThemeEnvironment {
    fun getTheme(): Flow<Theme>
    suspend fun setTheme(theme: Theme)
}
