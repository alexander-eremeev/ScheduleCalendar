package com.childmathematics.android.shiftschedule.database.presentation.countries

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childmathematics.android.shiftschedule.data.models.CountryEntity
import com.childmathematics.android.shiftschedule.data.models.UiResources
import com.childmathematics.android.shiftschedule.data.repository.CountryRepository
import com.childmathematics.android.shiftschedule.data.usecases.AddCountryUseCase
import com.childmathematics.android.shiftschedule.data.usecases.DeleteCountryUseCase
import com.childmathematics.android.shiftschedule.data.usecases.GetAllCountriesUseCase
import com.childmathematics.android.shiftschedule.data.usecases.GetCountryUseCase
import com.childmathematics.android.shiftschedule.data.usecases.SearchCountryUseCase
import com.childmathematics.android.shiftschedule.data.usecases.UpdateCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val repo: CountryRepository,

    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase,
    private val addCountryUseCase: AddCountryUseCase,
    private val updateCountryUseCase: UpdateCountryUseCase,
    private val deleteCountryUseCase: DeleteCountryUseCase,
    private val searchCountryUseCase: SearchCountryUseCase,
    /*

     */
)  : ViewModel(){

    private val _viewState = MutableStateFlow(CountryViewState())
    val viewState: StateFlow<CountryViewState> = _viewState
    private val _effectChannel = Channel<SnackbarEffect>()
    val effectFlow: Flow<SnackbarEffect> = _effectChannel.receiveAsFlow()

    private var recentlyDeletedCountry: CountryEntity? = null

    var countries by mutableStateOf(emptyList<CountryEntity>())
//    var country by mutableStateOf(Countries(0, "", ""))
    var openDialog by mutableStateOf(false)

    init {
    }
    fun handleIntent(intent: UserIntent) {
        when (intent) {
            is CountryViewIntent.LoadCountries -> loadCountries()
//            is UserIntent.AddUser -> validateAndAddUser(intent.name, intent.email, intent.imagePath)
//            is UserIntent.DeleteUser -> deleteUser(intent.user)
//            is UserIntent.ClearUsers -> clearUsers()
//            is UserIntent.SearchUser -> searchUsers(intent.query)
//            is UserIntent.UpdateName -> validateName(intent.name)
//            is UserIntent.UpdateEmail -> validateEmail(intent.email)
//            is UserIntent.UndoDelete -> undoDelete()
        }
    }
    fun getCountries() = viewModelScope.launch {
        repo.getCountriesFromRoom().collect{
            dbCountries ->
            countries = dbCountries
        }
    }
//----------------------------------------------------------------------
// Load users from the Room database using Flow and UIResources
private fun loadCountries() {
    viewModelScope.launch(Dispatchers.IO) { // Perform loading on the IO thread
        getAllCountriesUseCaseUseCase().collectLatest { resource ->
            when (resource) {
                is UiResources.Loading -> withContext(Dispatchers.Main) {
                    _viewState.update { it.copy(isLoading = true) }
                }
                is UIResources.Success -> withContext(Dispatchers.Main) {
                    _viewState.update {
                        it.copy(isLoading = false, users = resource.data, filteredUsersList =
                            resource.data)
                    }
                }
                is UIResources.Error -> withContext(Dispatchers.Main) {
                    _viewState.update { it.copy(isLoading = false) }
                    _effectChannel.send(SnackbarEffect.ShowSnackbar("Error loading users:
                        ${resource.message}"))
                }
            }
        }
    }
}
// ========================================================================
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


