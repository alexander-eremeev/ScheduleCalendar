package com.childmathematics.android.shiftschedule.database.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.childmathematics.android.shiftschedule.database.domain.model.Countries

@Database(entities = [Countries::class], version = 1, exportSchema = false)
abstract class CountryDB : RoomDatabase() {
    abstract fun countryDAO(): CountryDAO
}
