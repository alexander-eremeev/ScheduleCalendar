package com.childmathematics.android.shiftschedule.presentation.ui.countries.uimodels

import com.childmathematics.android.shiftschedule.data.models.CountryEntity


// ViewState для управления данными состояния пользовательского интерфейса
data  class CountryViewState (
    val isLoading: Boolean = false,
    val countries: List<CountryEntity> = emptyList(),
    val country: CountryEntity? = null ,
    val filteredCountriesList: List<CountryEntity> = emptyList(), // The filtered list of users
    val countryid: Int=0,
    val shortname: String = "",
    val longname: String = "",
    val nameError: Boolean = false,
    val searchQuery: String = "",
    val recentlyDeletedCountry: CountryEntity? = null  //
)