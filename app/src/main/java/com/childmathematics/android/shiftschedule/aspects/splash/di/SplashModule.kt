package com.childmathematics.android.shiftschedule.aspects.splash.di

import com.childmathematics.android.shiftschedule.aspects.splash.data.ISplashEnvironment
import com.childmathematics.android.shiftschedule.aspects.splash.data.SplashEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SplashModule {

    @Binds
    abstract fun provideEnvironment(
        environment: SplashEnvironment
    ): ISplashEnvironment

}
