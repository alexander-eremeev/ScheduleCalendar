package com.childmathematics.android.shiftschedule.database.presentation.countries

import com.childmathematics.android.shiftschedule.data.models.CountryEntity

// ViewState для управления данными состояния пользовательского интерфейса
class CountryViewState {
    val isLoading: Boolean = false
    val id: Int=0
    val shortName: String = ""
    val longName: String = ""
    val nameError: Boolean = false
    val searchQuery: String = ""
    val recentlyDeletedCountry: CountryEntity? = null  //
}