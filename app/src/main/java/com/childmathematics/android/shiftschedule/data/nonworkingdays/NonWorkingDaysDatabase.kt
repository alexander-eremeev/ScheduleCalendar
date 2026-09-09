package com.childmathematics.android.shiftschedule.data.nonworkingdays

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.childmathematics.android.shiftschedule.data.models.NonWorkingDaysEntity
import com.childmathematics.android.shiftschedule.data.models.CountryEntity
import com.childmathematics.android.shiftschedule.data.nonworkingdays.CountryDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Database class with a singleton Instance object.
 * Класс базы данных с одноэлементным объектом экземпляра.
 */
@Database(entities = [NonWorkingDaysEntity::class, CountryEntity::class], version = 1, exportSchema = true)
abstract class NonWorkingDaysDatabase : RoomDatabase() {
    abstract fun nonWorkingDaysDao(): NonWorkingDaysDao
    abstract fun countryDAO(): CountryDAO
}
