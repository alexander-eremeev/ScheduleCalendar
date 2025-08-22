package com.childmathematics.android.shiftschedule.data.repository

import com.childmathematics.android.shiftschedule.data.models.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    fun getCountriesFromRoom(): Flow<List<Country>>

    fun getCountryFromRoom(countryId: Int): Flow<Country>

    fun addCountryToRoom(shortName: Country)

    fun updateCountryInRoom(shortName: Country)

    fun deleteCountryFromRoom(shortName: Country)
}

