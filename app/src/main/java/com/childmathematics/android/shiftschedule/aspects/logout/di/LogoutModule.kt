package com.childmathematics.android.shiftschedule.aspects.logout.di

import com.childmathematics.android.shiftschedule.aspects.logout.data.ILogoutEnvironment
import com.childmathematics.android.shiftschedule.aspects.logout.data.LogoutEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LogoutModule {

    @Binds
    abstract fun provideEnvironment(
        environment: LogoutEnvironment
    ): ILogoutEnvironment

}
