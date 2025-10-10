package com.childmathematics.android.shiftschedule.database.presentation.countries

import com.childmathematics.android.shiftschedule.data.models.CountryEntity

// ViewIntent для обработки действий пользователя
sealed class CountryViewIntent {
    data object LoadCountries : CountryViewIntent() // Intent for loading
    data class AddCountry(val shortname: String, val longname: String, val id: Int?) : CountryViewIntent() //
    data class DeleteCountry(val shortname: CountryEntity) : CountryViewIntent() // Intent for deleting a user
    data object ClearCountries : CountryViewIntent() // Intent for clearing users
    data class SearchCountry(val query: String) : CountryViewIntent() // Intent for searching users
    data class UpdateLongName(val name: String) : CountryViewIntent() // Intent for updating name input
    data object UndoDelete : CountryViewIntent() // Intent for undoing a delete

}