package com.childmathematics.android.shiftschedule.database.presentation.countries

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childmathematics.android.shiftschedule.database.domain.model.Countries
import com.childmathematics.android.shiftschedule.database.domain.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val repo: CountryRepository
)  : ViewModel(){
    var countries by mutableStateOf(emptyList<Countries>())
    var country by mutableStateOf(Countries(0, "", ""))
    var openDialog by mutableStateOf(false)

    fun getCountries() = viewModelScope.launch {
        repo.getCountriesFromRoom().collect{
            dbCountries ->
            countries = dbCountries
        }
    }

    fun getCountry(id: Int) = viewModelScope.launch {
        repo.getCountryFromRoom(id).collect{
                dbCountry ->
            country = dbCountry
        }
    }
    fun addCountry(country: Countries) = viewModelScope.launch(Dispatchers.IO) {
        repo.addCountryToRoom(country)
    }
    fun updateCountry(country: Countries) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateCountryInRoom(country)
    }
    fun deleteCountry(country: Countries) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteCountryFromRoom(country)
    }
    fun updateLongName(longName: String) {
        country = country.copy(
            longName = longName
        )
    }
    fun updateShortName(shortName: String) {
        country = country.copy(
            shortName = shortName
        )
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }

}
