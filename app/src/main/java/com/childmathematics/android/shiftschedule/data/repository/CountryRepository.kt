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

    suspend fun addCountryToRoom(shortName: CountryEntity)

    suspend fun updateCountryInRoom(shortName: CountryEntity)

    suspend fun deleteCountryFromRoom(shortName: CountryEntity)

    fun searchCountryFromRoom(query: Int): Flow<UiResources<CountryEntity>>
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

        override suspend fun addCountryToRoom(shortName: CountryEntity) {
            try {
                    countryDao.addCountry(shortName)
                }
            catch (e: Exception) { //TooGenericExceptionCaught: Перехваченное исключение слишком общее.
                                    // Предпочтите перехват конкретных исключений, а не текущего случая.
                throw e // Позвольте ViewModel обработать любое перехваченное исключение
                }
        }

        override suspend fun updateCountryInRoom(shortName: CountryEntity) {
            try {
                countryDao.updateCountry(shortName)
            }
            catch (e: Exception) { //TooGenericExceptionCaught: Перехваченное исключение слишком общее.
                // Предпочтите перехват конкретных исключений, а не текущего случая.
                throw e // Позвольте ViewModel обработать любое перехваченное исключение
            }
        }

        override suspend fun deleteCountryFromRoom(shortName: CountryEntity) {
            try {
                countryDao.deleteCountry(shortName)
            }
            catch (e: Exception) { //TooGenericExceptionCaught: Перехваченное исключение слишком общее.
                // Предпочтите перехват конкретных исключений, а не текущего случая.
                throw e // Позвольте ViewModel обработать любое перехваченное исключение
            }       }

        override fun searchCountryFromRoom(query: Int): Flow<UiResources<CountryEntity>> = flow {

            emit(UiResources.Loading)
            countryDao.getCountry(query).collect { countries ->
                emit(UiResources.Success(countries))
                }
            }.catch { e ->
                emit(UiResources.Error(e.localizedMessage ?: "Unknown error occurred"))
                }

}




