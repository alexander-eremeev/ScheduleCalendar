package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.domain.repository.NonWorkingDaysRepository
import com.childmathematics.android.shiftschedule.domain.usecases.DeleteNonWorkingDayUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.GetAllCountriesUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.GetAllNonWorkingDaysUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.GetCountryUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.GetNonWorkingDayUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.InsertNonWorkingDayUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.SearchCountryUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.SearchNonWorkingDayUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.UpdateCountryUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.UpdateNonWorkingDayUseCase
import com.childmathematics.android.shiftschedule.presentation.ui.countries.uimodels.CountryViewIntent
import com.childmathematics.android.shiftschedule.presentation.ui.countries.uimodels.CountryViewState
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.uimodels.NonWorkingDaysViewIntent
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.uimodels.NonWorkingDaysViewState
import com.childmathematics.android.shiftschedule.presentation.util.SnackbarEffect
import com.childmathematics.android.shiftschedule.presentation.util.UiResources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

/*
@HiltViewModel
internal class HelpViewModel @Inject constructor(
    private val itemsRepository: ItemsRepository,
) : ViewModel() {

    val products = productsRepository.getFavorites()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}

 */
/**
 * ViewModel to retrieve all items in the Room database.
 * ViewModel для получения всех элементов в базе данных Room.
 */
@HiltViewModel
class NonWorkingDaysViewModel @Inject constructor(
    private val nonWorkingDaysRepository: NonWorkingDaysRepository,

    private val getNonWorkingDaysUseCase: GetAllNonWorkingDaysUseCase,
    private val getNonWorkingDayUseCase: GetNonWorkingDayUseCase,
    private val insertNonWorkingDayUseCase: InsertNonWorkingDayUseCase,
    private val updateNonWorkingDayUseCase: UpdateNonWorkingDayUseCase,
    private val deleteNonWorkingDayUseCase: DeleteNonWorkingDayUseCase,
    private val searchNonWorkingDayUseCase: SearchNonWorkingDayUseCase,
    ): ViewModel() {
//class NonWorkingDaysViewModel(itemsRepository: ItemsRepository) : ViewModel() {


    /**
     *      * Holds home ui state. The list of items are retrieved from [ItemsRepository] and mapped to
     * [HomeUiState]
     * Сохраняет исходное состояние пользовательского интерфейса. Список элементов извлекается
     * из [ItemsRepository] и сопоставляется с [HomeUiState]
     */
    /*
    sealed class NonWorkingDaysViewIntent {
    data object LoadNonWorkingDays : NonWorkingDaysViewIntent() // Intent for loading
    data class LoadNonWorkingDay(val nonWorkingDayId: Int) : NonWorkingDaysViewIntent() // Intent for loading
    data class InsertNonWorkingDay(val shortName: String, val longName: String, val nonWorkingDaysId: Int?) : NonWorkingDaysViewIntent() //
    data class DeleteNonWorkingDay(val shortName: String) : NonWorkingDaysViewIntent() // Intent for deleting a user
    data object ClearNonWorkingDays : NonWorkingDaysViewIntent() // Intent for clearing users
    data class SearchNonWorkingDay(val query: String) : NonWorkingDaysViewIntent() // Intent for searching users
    data class UpdateNonWorkingDay(val name: String) : NonWorkingDaysViewIntent() // Intent for updating name input
    data object UndoDeleteNonWorkingDay : NonWorkingDaysViewIntent() // Intent for undoing a delete

}
    val nonWorkingDays01PageUiState: StateFlow<NonWorkingDays01PageUiState> =
        itemsRepository.getAllItemsStream().map { NonWorkingDays01PageUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = NonWorkingDays01PageUiState()
            )
     */
    private val _viewState = MutableStateFlow(NonWorkingDaysViewState())
    val viewState: StateFlow<NonWorkingDaysViewState> = _viewState
    private val _effectChannel = Channel<SnackbarEffect>()
    val effectFlow: Flow<SnackbarEffect> = _effectChannel.receiveAsFlow()


    init {
        handleIntent(NonWorkingDaysViewIntent.LoadNonWorkingDays)
    }


    fun handleIntent(intent: NonWorkingDaysViewIntent) {
        when (intent) {
            is NonWorkingDaysViewIntent.LoadNonWorkingDays -> loadNonWorkingDays()
            is NonWorkingDaysViewIntent.LoadNonWorkingDay -> loadNonWorkingDay(intent.nonWorkingDayId)
            is NonWorkingDaysViewIntent.InsertNonWorkingDay -> validateAndAddNonWorkingDay(
                intent.shortName, intent.longName, intent.nonWorkingDaysId
            )

            is NonWorkingDaysViewIntent.DeleteNonWorkingDay -> deleteNonWorkingDay(intent.shortName)
            is NonWorkingDaysViewIntent.UndoDeleteNonWorkingDay-> undoDeleteNonWorkingDay()

            is NonWorkingDaysViewIntent.ClearNonWorkingDays -> clearNonWorkingDays()
            is NonWorkingDaysViewIntent.SearchNonWorkingDay -> searchNonWorkingDay(intent.query)
            is NonWorkingDaysViewIntent.UpdateNonWorkingDay -> updateNonWorkingDay(intent.name)
        }
    }


    //----------------------------------------------------------------------
    // Load  from the Room database using Flow and UIResources
    private fun loadNonWorkingDays() {

        viewModelScope.launch(Dispatchers.IO) { // Perform loading on the IO thread
            getNonWorkingDaysUseCase().collectLatest { resource ->
                when (resource) {
                    is UiResources.Loading -> withContext(Dispatchers.Main) {
                        _viewState.update { it.copy(isLoading = true) }
                    }

                    is UiResources.Success -> withContext(Dispatchers.Main) {
                        _viewState.update {

                            it.copy(
                                isLoading = false, nonWorkingDays = resource.data,
                                filteredNonWorkingDaysList = resource.data
                            )
                        }
                        _effectChannel.send(
                            SnackbarEffect.ShowSnackbar(
                                "Ошибки загрузки данных из БД:  Нет"
                            )
                        )
                    }

                    is UiResources.Error -> withContext(Dispatchers.Main) {
                        _viewState.update { it.copy(isLoading = false) }
                        _effectChannel.send(
                            SnackbarEffect.ShowSnackbar(
                                "Ошибка загрузки данных из БД:  ${resource.message}"
                            )
                        )
                    }
                }
            }
        }
    }

    //----------------------------------------------------------------------
    // Load  from the Room database using Flow and UIResources
    private fun loadNonWorkingDay(countryId: Int) {

        viewModelScope.launch(Dispatchers.IO) { // Perform loading on the IO thread
            getNonWorkingDayUseCase(countryId).collectLatest { resource ->
                when (resource) {
                    is UiResources.Loading -> withContext(Dispatchers.Main) {
                        _viewState.update { it.copy(isLoading = true) }
                    }

                    is UiResources.Success -> withContext(Dispatchers.Main) {
                        _viewState.update {

                            it.copy(
                                isLoading = false, nonWorkingDay = resource.data
                            )
                        }
                    }

                    is UiResources.Error -> withContext(Dispatchers.Main) {
                        _viewState.update { it.copy(isLoading = false) }
                        _effectChannel.send(
                            SnackbarEffect.ShowSnackbar(
                                "Ошибка загрузки данных из БД: ${resource.message}"
                            )
                        )
                    }
                }
            }
        }
    }

    //-----------------------------------------------------------------------------------
    private fun validateAndAddNonWorkingDay(shortName: String, longName: String, countryId: Int?) {}

    private fun deleteNonWorkingDay(shortName: String) {}

    private fun undoDeleteNonWorkingDay() {}

    private fun clearNonWorkingDays() {}

    private fun searchNonWorkingDay(query: String) {}

    private fun updateNonWorkingDay(longName: String) {}
}



