package com.childmathematics.android.shiftschedule.aspects.setting.ui

import androidx.compose.runtime.Immutable
import com.childmathematics.android.shiftschedule.R

@Immutable
data class SettingState(
    val items: List<SettingItem> = initial()
) {
    companion object {
        private fun initial() = listOf(
            SettingItem.Theme(R.string.setting_theme),
            SettingItem.Language(R.string.setting_language),
            // SettingItem.Logout(R.string.setting_logout),
        )
    }
}

sealed class SettingItem(open val title: Int) {
    data class Theme(override val title: Int) : SettingItem(title)
    data class Logout(override val title: Int) : SettingItem(title)
    data class Language(override val title: Int) : SettingItem(title)
}
