package com.childmathematics.android.shiftschedule.data.repository

import com.childmathematics.android.shiftschedule.data.models.CountryEntity
import kotlinx.coroutines.flow.Flow
import jakarta.inject.Inject
import com.childmathematics.android.shiftschedule.data.models.UiResources
import com.childmathematics.android.shiftschedule.data.nonworkingdays.CountryDAO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow


interface CountryRepository {
    fun getAllCountriesFromRoom(): Flow<UiResources<List<CountryEntity>>>

    fun getCountryFromRoom(countryId: Int): Flow<CountryEntity>

    fun addCountryToRoom(shortName: CountryEntity)

    fun updateCountryInRoom(shortName: CountryEntity)

    fun deleteCountryFromRoom(shortName: CountryEntity)
}

class CountryRepositoryImpl @Inject constructor(
    private val countryDao: CountryDAO) : CountryRepository {
    override fun getAllCountriesFromRoom(): Flow<UiResources<List<CountryEntity>>> = flow {
        emit(UiResources.Loading)
        countryDao.getAllCountries().collect { countries ->
            emit(UiResources.Success(countries))
        }
    }.catch { e ->
        emit(UiResources.Error(e.localizedMessage ?: "Unknown error occurred"))
    }

    override fun getCountryFromRoom(countryId: Int): Flow<CountryEntity> {
        TODO("Not yet implemented")
    }

    override fun addCountryToRoom(shortName: CountryEntity) {
        TODO("Not yet implemented")
    }

    override fun updateCountryInRoom(shortName: CountryEntity) {
        TODO("Not yet implemented")
    }

    override fun deleteCountryFromRoom(shortName: CountryEntity) {
        TODO("Not yet implemented")
    }
}


