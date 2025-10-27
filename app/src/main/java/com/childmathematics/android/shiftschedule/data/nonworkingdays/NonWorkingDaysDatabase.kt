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
@Database(entities = [NonWorkingDaysEntity::class, CountryEntity::class], version = 1, exportSchema = false)
abstract class NonWorkingDaysDatabase : RoomDatabase() {
    abstract fun nonWorkingDaysDao(): NonWorkingDaysDao
    abstract fun countryDAO(): CountryDAO
/*
    companion object {
        @Volatile
        private var INSTANCE: NonWorkingDaysDatabase? = null

        fun getDatabase(context: Context): NonWorkingDaysDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    NonWorkingDaysDatabase::class.java,
                    "nonWorkingDays_database"
                )
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     * Установка этой опции в конструкторе базы данных вашего приложения означает, что Room
                     * безвозвратно удаляет все данные из таблиц вашей базы данных, когда они
                     * пытается выполнить миграцию без определенного пути миграции.
                     */
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
*/
}
/*
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NonWorkingDaysDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NonWorkingDaysDatabase::class.java,
            "nonWorkingDays_database"
        )
        .fallbackToDestructiveMigration(false)
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
*/