package com.childmathematics.android.shiftschedule.database.data.repository

import com.childmathematics.android.shiftschedule.database.data.network.CountryDAO
import com.childmathematics.android.shiftschedule.database.domain.model.Countries
import com.childmathematics.android.shiftschedule.database.domain.repository.CountryRepository

class CountryRepositoryImp (
    private val countryDao: CountryDAO
) : CountryRepository {
    override fun getCountriesFromRoom() = countryDao.getCountries()

    override fun getCountryFromRoom(countryId: Int) = countryDao.getCountry(countryId)

    override fun addCountryToRoom(shortName: Countries) = countryDao.addCountry(shortName)

    override fun updateCountryInRoom(shortName: Countries) = countryDao.updateCountry(shortName)

    override fun deleteCountryFromRoom(shortName: Countries) = countryDao.deleteCountry(shortName)

}
