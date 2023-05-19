package com.childmathematics.android.shiftschedule.aspects.localized.setting.data

import kotlinx.coroutines.flow.Flow
import com.childmathematics.android.shiftschedule.model.Language



interface ILocalizedSettingEnvironment {
    fun getLanguage(): Flow<Language>
    suspend fun setLanguage(language: com.childmathematics.android.shiftschedule.model.Language)
}

