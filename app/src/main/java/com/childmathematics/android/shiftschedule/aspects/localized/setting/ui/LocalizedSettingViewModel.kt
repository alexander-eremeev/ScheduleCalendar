package com.childmathematics.android.shiftschedule.aspects.localized.setting.ui

import androidx.lifecycle.viewModelScope
import com.childmathematics.android.basement.core.viewmodel.StatefulViewModel
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.aspects.localized.setting.data.ILocalizedSettingEnvironment
import com.childmathematics.android.shiftschedule.basis.extension.select
import com.childmathematics.android.shiftschedule.model.Language
//import com.childmathematics.android.shiftschedule.aspects.localized.setting.data.ILocalizedSettingEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalizedSettingViewModel @Inject constructor(localizedSettingEnvironment: ILocalizedSettingEnvironment) :
    StatefulViewModel<LocalizedSettingState, LocalizedEffect, LocalizedSettingAction, ILocalizedSettingEnvironment>(LocalizedSettingState(), localizedSettingEnvironment) {

    init {
        initLanguage()
    }

    override fun dispatch(action: LocalizedSettingAction) {
        when (action) {
            is LocalizedSettingAction.SelectLanguage -> {
                viewModelScope.launch {
//                    environment.setLanguage(action.selected.language)
                    environment.setLanguage(action.selected.language)
                    setEffect(LocalizedEffect.ApplyLanguage(action.selected.language))
                }
            }
        }
    }

    private fun initLanguage() {
        viewModelScope.launch {
            delay(100)
            environment.getLanguage()
                .collect {
//                    setState { copy(items = initial().select(it)) }
                    setState { copy(items = initial().select(it)) }
                }
        }
    }

    private fun initial() = listOf(
        LanguageItem(
            title = R.string.setting_language_english,
            language = Language.ENGLISH,
            applied = false
        ),
        LanguageItem(
            title = R.string.setting_language_indonesia,
            language = Language.INDONESIA,
            applied = false
        ),
    )

}
