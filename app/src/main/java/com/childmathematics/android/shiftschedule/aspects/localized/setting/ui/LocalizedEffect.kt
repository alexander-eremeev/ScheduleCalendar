package com.childmathematics.android.shiftschedule.aspects.localized.setting.ui

import com.childmathematics.android.shiftschedule.model.Language

sealed class LocalizedEffect {
    data class ApplyLanguage(val language: Language) : LocalizedEffect()
}
