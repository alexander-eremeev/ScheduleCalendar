package com.childmathematics.android.shiftschedule.aspects.localized.setting.ui

import androidx.compose.runtime.Immutable
import com.childmathematics.android.shiftschedule.model.Language

@Immutable
data class LocalizedSettingState(val items: List<LanguageItem> = listOf())

data class LanguageItem(
    val title: Int,
    val language: Language,
    val applied: Boolean
)
