package com.childmathematics.android.shiftschedule.basis.di

import com.childmathematics.android.shiftschedule.basis.wrapper.DateTimeProvider
import com.childmathematics.android.shiftschedule.basis.wrapper.DateTimeProviderImpl
import com.childmathematics.android.shiftschedule.basis.wrapper.IdProvider
import com.childmathematics.android.shiftschedule.basis.wrapper.IdProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Singleton
    @Provides
    fun provideIdProvider(): IdProvider {
        return IdProviderImpl()
    }

    @Singleton
    @Provides
    fun provideDateTimeProvider(): DateTimeProvider {
        return DateTimeProviderImpl()
    }

}
