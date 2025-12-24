package com.childmathematics.android.shiftschedule.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.childmathematics.android.shiftschedule.data.nonworkingdays.CountryDAO
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
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Empty implementation, because the schema isn't changing.
        }
    }
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NonWorkingDaysDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NonWorkingDaysDatabase::class.java,
            "nonWorkingDays_database"
        )
            .createFromAsset("database/ScheduleCalendar.db")
            .fallbackToDestructiveMigration(true)
 //           .addMigrations(MIGRATION_1_2)
            .build()

        //    .also { INSTANCE = it }
    }
    @Singleton
    @Provides
    fun provideCountryDAO(db: NonWorkingDaysDatabase): CountryDAO {
        return db.countryDAO()
    }
    @Singleton
    @Provides
    fun provideNonWorkingDaysDao(db: NonWorkingDaysDatabase): NonWorkingDaysDao {
        return db.nonWorkingDaysDao()
    }
}
