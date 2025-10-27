package com.childmathematics.android.shiftschedule.presentation.countries


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childmathematics.android.shiftschedule.data.models.CountryEntity
import com.childmathematics.android.shiftschedule.domain.repository.CountryRepository
import com.childmathematics.android.shiftschedule.domain.usecases.AddCountryUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.DeleteCountryUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.GetAllCountriesUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.GetCountryUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.SearchCountryUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.UpdateCountryUseCase
import com.childmathematics.android.shiftschedule.data.models.UiResources
import com.childmathematics.android.shiftschedule.presentation.countries.uimodels.CountryViewIntent
import com.childmathematics.android.shiftschedule.presentation.countries.uimodels.CountryViewState
import com.childmathematics.android.shiftschedule.presentation.countries.uimodels.SnackbarEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/*
import kotlinx.coroutines.Job

 */
@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countryRepository: CountryRepository,

    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase,
    private val addCountryUseCase: AddCountryUseCase,
    private val updateCountryUseCase: UpdateCountryUseCase,
    private val deleteCountryUseCase: DeleteCountryUseCase,
    private val searchCountryUseCase: SearchCountryUseCase,


)  : ViewModel(){

    private val _viewState = MutableStateFlow(CountryViewState())
    val viewState: StateFlow<CountryViewState> = _viewState
    private val _effectChannel = Channel<SnackbarEffect>()
    val effectFlow: Flow<SnackbarEffect> = _effectChannel.receiveAsFlow()

    private var recentlyDeletedCountry: CountryEntity? = null
/*
    var countries by mutableStateOf(emptyList<CountryEntity>())
//    var country by mutableStateOf(Countries(0, "", ""))
    var openDialog by mutableStateOf(false)

 */

        init {
        }
        fun handleIntent(intent: CountryViewIntent) {
            when (intent) {
                is CountryViewIntent.LoadCountries -> loadCountries()
                is CountryViewIntent.LoadCountry -> loadCountry(intent.countryId)
                is CountryViewIntent.AddCountry -> validateAndAddCountry(
                        intent.shortName, intent.longName, intent.countryId)
                is CountryViewIntent.DeleteCountry -> deleteCountry(intent.shortName)
                is CountryViewIntent.ClearCountries -> clearCountries()
                is CountryViewIntent.SearchCountry -> searchCountry(intent.query)
                is CountryViewIntent.UpdateLongName -> updateLongName(intent.name)
                is CountryViewIntent.UndoDelete -> undoDelete()
             }
        }


    //----------------------------------------------------------------------
    // Load  from the Room database using Flow and UIResources
    private fun loadCountries() {

        viewModelScope.launch(Dispatchers.IO) { // Perform loading on the IO thread
            getAllCountriesUseCase().collectLatest { resource ->
                when (resource) {
                    is UiResources.Loading -> withContext(Dispatchers.Main) {
                        _viewState.update {it.copy(isLoading =  true) }
                    }
                    is UiResources.Success -> withContext(Dispatchers.Main) {
                        _viewState.update {

                            it.copy(isLoading = false, countries = resource.data,
                                        filteredCountriesList = resource.data
                                    )
                        }
                    }
                    is UiResources.Error -> withContext(Dispatchers.Main) {
                        _viewState.update {it.copy(isLoading = false ) }
                        _effectChannel.send(
                            SnackbarEffect.ShowSnackbar(
                            "Error loading users: ${resource.message}"))
                    }
                }
            }
        }
    }
    //----------------------------------------------------------------------
    // Load  from the Room database using Flow and UIResources
    private fun loadCountry(countryId: Int) {

        viewModelScope.launch(Dispatchers.IO) { // Perform loading on the IO thread
            getCountryUseCase(countryId).collectLatest { resource ->
                when (resource) {
                    is UiResources.Loading -> withContext(Dispatchers.Main) {
                        _viewState.update {it.copy(isLoading =  true) }
                    }
                    is UiResources.Success -> withContext(Dispatchers.Main) {
                        _viewState.update {

                            it.copy(isLoading = false, country = resource.data
                            )
                        }
                    }
                    is UiResources.Error -> withContext(Dispatchers.Main) {
                        _viewState.update {it.copy(isLoading = false ) }
                        _effectChannel.send(
                            SnackbarEffect.ShowSnackbar(
                                "Error loading users: ${resource.message}"))
                    }
                }
            }
        }
    }

    //-----------------------------------------------------------------------------------
    private fun validateAndAddCountry(shortName: String, longName: String, countryId: Int?)
    {}

    private fun deleteCountry(shortName: String)
    {}

    private fun undoDelete()
    {}

    private fun clearCountries()
    {}

    private fun searchCountry(query: String)
    {}

    private fun updateLongName(longName: String)
    {}
    /*

Проверка и добавление новой страны (отмена задания не требуется)
private fun validateAndAddCountry (val shortname: String, val longname: String, val id: Int?) {
    val nameError = !name.isValidName()
    val emailError = !email.isValidEmail()
    val imageError = !imagePath.isValidImage()
    // Проверка корректности имени, email и изображения
    if (nameError || emailError || imageError) {
        val errorMessage = buildErrorMessage(nameError, emailError, imageError)
        _viewState.update { it.copy(nameError = nameError, emailError = emailError) }
        viewModelScope.launch(Dispatchers.Main) {
        _effectChannel.send(SnackbarEffect.ShowSnackbar(errorMessage))
    }
    return
    }
    _viewState.update { it.copy(isLoading = true ) }
    viewModelScope.launch(Dispatchers.IO) { // Выполнение операций с базой данных в потоке  ввода-вывода
        try {
            val imageUrl = imagePath // Сохранение изображения во внутреннем хранилище
            // Создание UserEntity и добавление его в базу данных Room
            addUserUseCase(UserEntity(name = name, email = email, imageUrl = imageUrl))
            withContext(Dispatchers.Main) {
            _viewState.update {
            it.copy(
            isLoading = false ,
            name = "" ,
            email = "" ,
            nameError = false ,
            emailError = false
            )
            }
            _effectChannel.send(SnackbarEffect.ShowSnackbar( "Пользователь успешно добавлен!"
            ))
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                _viewState.update { it.copy(isLoading = false ) }
                _effectChannel.send(SnackbarEffect.ShowSnackbar("Ошибка добавления пользователя:
                ${e.message} " ))
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

     */

}