package com.childmathematics.android.shiftschedule.data.di

import android.content.Context
import androidx.room.Room
import com.childmathematics.android.shiftschedule.data.nonworkingdays.NonWorkingDaysDao
import com.childmathematics.android.shiftschedule.data.nonworkingdays.NonWorkingDaysDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideNonWorkingDaysDatabase(@ApplicationContext appContext: Context): NonWorkingDaysDatabase {
        return Room.databaseBuilder(
            appContext,
            NonWorkingDaysDatabase::class.java,
            "NonWorkingDays_database"
        ).build()
//        ).fallbackToDestructiveMigration().build()
    }
    @Singleton
    @Provides
    fun provideNonWorkingDaysDao(nonWorkingDaysDatabase: NonWorkingDaysDatabase): NonWorkingDaysDao {
        return nonWorkingDaysDatabase.nonWorkingDaysDao()
    }
}