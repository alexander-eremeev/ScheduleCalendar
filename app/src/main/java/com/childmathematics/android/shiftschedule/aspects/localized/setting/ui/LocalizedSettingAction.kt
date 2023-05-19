package com.childmathematics.android.shiftschedule.aspects.localized.setting.ui

sealed class LocalizedSettingAction {
    data class SelectLanguage(val selected: LanguageItem) : LocalizedSettingAction()
}
