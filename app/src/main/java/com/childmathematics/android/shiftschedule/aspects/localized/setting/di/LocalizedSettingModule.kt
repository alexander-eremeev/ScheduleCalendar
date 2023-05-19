package com.childmathematics.android.shiftschedule.aspects.localized.setting.di

import com.childmathematics.android.shiftschedule.aspects.localized.setting.data.ILocalizedSettingEnvironment
import com.childmathematics.android.shiftschedule.aspects.localized.setting.data.LocalizedSettingEnvironment
//import com.childmathematics.android.shiftschedule.aspects.localized.setting.data.ILocalizedSettingEnvironment
//import com.childmathematics.android.shiftschedule.aspects.localized.setting.data.LocalizedSettingEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalizedSettingModule {

    @Binds
    abstract fun provideEnvironment(
        environment: LocalizedSettingEnvironment
    ): ILocalizedSettingEnvironment

}
