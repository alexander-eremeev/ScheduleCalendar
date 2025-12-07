package com.childmathematics.android.shiftschedule.domain.usecases

import com.childmathematics.android.shiftschedule.data.models.CountryEntity
import com.childmathematics.android.shiftschedule.presentation.util.UiResources
import com.childmathematics.android.shiftschedule.domain.repository.CountryRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllCountriesUseCase @Inject constructor (
    private val countryRepository: CountryRepository
) {
    operator fun invoke () : Flow<UiResources<List<CountryEntity>>> {
        return countryRepository.getAllCountriesFromRoom()
    }
}

class GetCountryUseCase @Inject constructor (
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke (countryId: Int): Flow<UiResources<CountryEntity>> {
        return countryRepository.getCountryFromRoom(countryId)
    }
}

class AddCountryUseCase @Inject constructor (
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke (shortName: CountryEntity){
        return countryRepository.addCountryToRoom(shortName)
    }
}

class UpdateCountryUseCase @Inject constructor (
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke (shortName: CountryEntity){
        return countryRepository.updateCountryInRoom(shortName)
    }
}

class DeleteCountryUseCase @Inject constructor (
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke (shortName: CountryEntity){
        return countryRepository.deleteCountryFromRoom(shortName)
    }
}

class SearchCountryUseCase @Inject constructor (
    private val countryRepository: CountryRepository
) {
    operator fun invoke (query: String) : Flow<UiResources<List<CountryEntity>>> {
        return countryRepository.searchCountryFromRoom(query)
    }
}
