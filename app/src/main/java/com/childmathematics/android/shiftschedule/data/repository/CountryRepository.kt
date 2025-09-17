package com.childmathematics.android.shiftschedule.data.repository

import com.childmathematics.android.shiftschedule.data.models.CountryEntity
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getCountriesFromRoom(): Flow<List<CountryEntity>>

    fun getCountryFromRoom(countryId: Int): Flow<CountryEntity>

    fun addCountryToRoom(shortName: CountryEntity)

    fun updateCountryInRoom(shortName: CountryEntity)

    fun deleteCountryFromRoom(shortName: CountryEntity)
}

