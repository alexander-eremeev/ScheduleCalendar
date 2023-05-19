package com.childmathematics.android.shiftschedule.aspects.theme.data

import com.childmathematics.android.shiftschedule.basis.datasource.preference.PreferenceManager
import com.childmathematics.android.shiftschedule.model.Theme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeEnvironment @Inject constructor(
    private val preferenceManager: PreferenceManager
) : IThemeEnvironment {

    override fun getTheme(): Flow<Theme> {
        return preferenceManager.getTheme()
    }

    override suspend fun setTheme(theme: Theme) {
        preferenceManager.setTheme(theme)
    }

}
