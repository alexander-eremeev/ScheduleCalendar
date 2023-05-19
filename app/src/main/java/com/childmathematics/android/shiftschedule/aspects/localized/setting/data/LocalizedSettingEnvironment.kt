package com.childmathematics.android.shiftschedule.aspects.localized.setting.data
/*

import com.childmathematics.android.composetodolist.basis.datasource.preference.PreferenceManager
import com.childmathematics.android.shiftschedule.model.Language
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

 */
import com.childmathematics.android.shiftschedule.basis.datasource.preference.PreferenceManager
import com.childmathematics.android.shiftschedule.model.Language
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalizedSettingEnvironment @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ILocalizedSettingEnvironment {

    override fun getLanguage(): Flow<Language> {
//        override fun getLanguage(): Flow<Language> {
        return preferenceManager.getLanguage()
    }

    override suspend fun setLanguage(language: Language) {
        preferenceManager.setLanguage(language)
    }

}
