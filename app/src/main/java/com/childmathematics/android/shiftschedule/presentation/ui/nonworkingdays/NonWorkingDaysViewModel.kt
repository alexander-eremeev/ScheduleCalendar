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
import com.childmathematics.android.shiftschedule.domain.usecases.GetAllHoliDaysUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.GetAllHoliDaysYearUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.GetAllNonWorkingDaysUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.GetAllNonWorkingDaysYearUseCase
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
import kotlinx.coroutines.delay
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
import kotlin.collections.forEach

/**
 * ViewModel to retrieve all items in the Room database.
 * ViewModel для получения всех элементов в базе данных Room.
 */
@HiltViewModel
class NonWorkingDaysViewModel @Inject constructor(
    private val nonWorkingDaysRepository: NonWorkingDaysRepository,
    private val getHoliDaysYearUseCase: GetAllHoliDaysYearUseCase,
    private val getHoliDaysUseCase: GetAllHoliDaysUseCase,
    private val getNonWorkingDaysYearUseCase: GetAllNonWorkingDaysYearUseCase,
    private val getNonWorkingDaysUseCase: GetAllNonWorkingDaysUseCase,
    private val getNonWorkingDayUseCase: GetNonWorkingDayUseCase,
    private val insertNonWorkingDayUseCase: InsertNonWorkingDayUseCase,
    private val updateNonWorkingDayUseCase: UpdateNonWorkingDayUseCase,
    private val deleteNonWorkingDayUseCase: DeleteNonWorkingDayUseCase,
    private val searchNonWorkingDayUseCase: SearchNonWorkingDayUseCase,
    ): ViewModel() {
    /**
     *      * Holds home ui state. The list of items are retrieved from [ItemsRepository] and mapped to
     * [HomeUiState]
     * Сохраняет исходное состояние пользовательского интерфейса. Список элементов извлекается
     * из [ItemsRepository] и сопоставляется с [HomeUiState]
     */

    private val _viewState = MutableStateFlow(NonWorkingDaysViewState())
    val viewState: StateFlow<NonWorkingDaysViewState> = _viewState
    private val _effectChannel = Channel<SnackbarEffect>()
    val effectFlow: Flow<SnackbarEffect> = _effectChannel.receiveAsFlow()


    init {
//        handleIntent(NonWorkingDaysViewIntent.LoadNonWorkingDays)
    }

    fun handleIntent(intent: NonWorkingDaysViewIntent) {
        when (intent) {
            is NonWorkingDaysViewIntent.LoadHoliDaysYear -> loadHoliDaysYear(intent.year)
            is NonWorkingDaysViewIntent.LoadHoliDays -> loadHoliDays(intent.year,intent.month)
            is NonWorkingDaysViewIntent.LoadHoliOnlyDays -> loadHoliDaysOnlyDays(NonWorkingDaysViewState())
            is NonWorkingDaysViewIntent.LoadNonWorkingDaysYear -> loadNonWorkingDaysYear(intent.year)
            is NonWorkingDaysViewIntent.LoadNonWorkingDays -> loadNonWorkingDays(intent.year,intent.month)
            is NonWorkingDaysViewIntent.LoadNonWorkingOnlyDays -> loadNonWorkingDaysOnlyDays(NonWorkingDaysViewState())
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
    //----------------------------------------------------------------------
    // Load  from the Room database using Flow and UIResources
    private fun loadHoliDaysYear(year: Int) {

        viewModelScope.launch(Dispatchers.IO) { // Perform loading on the IO thread
            getHoliDaysYearUseCase(year).collectLatest { resource ->
                when (resource) {
                    is UiResources.Loading -> withContext(Dispatchers.Main) {
                        _viewState.update { it.copy(isLoading = true) }
                    }
                    is UiResources.Success -> withContext(Dispatchers.Main) {
                        _viewState.update {
                            it.copy(
                                isLoading = false, holiDaysYear = resource.data,
    //                            filteredNonWorkingDaysList = resource.data
                            )
                        }
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
    private fun loadNonWorkingDaysYear(year: Int) {

        viewModelScope.launch(Dispatchers.IO) { // Perform loading on the IO thread
            getNonWorkingDaysYearUseCase(year).collectLatest { resource ->
                when (resource) {
                    is UiResources.Loading -> withContext(Dispatchers.Main) {
                        _viewState.update { it.copy(isLoading = true) }
                    }
                    is UiResources.Success -> withContext(Dispatchers.Main) {
                        _viewState.update {
                            it.copy(
                                isLoading = false, nonWorkingDaysYear = resource.data,
    //                            filteredNonWorkingDaysList = resource.data
                            )
                        }
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
    private fun loadHoliDays(year : Int,month : Int) {

        viewModelScope.launch(Dispatchers.IO) { // Perform loading on the IO thread
            getHoliDaysUseCase(year,month).collectLatest { resource ->
                when (resource) {
                    is UiResources.Loading -> withContext(Dispatchers.Main) {
                        _viewState.update { it.copy(isLoading = true) }
                    }

                    is UiResources.Success -> withContext(Dispatchers.Main) {
                        _viewState.update {

                            it.copy(
                                isLoading = false, holiDays = resource.data,
//                                filteredNonWorkingDaysList = resource.data
                            )
                        }
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
    private fun loadNonWorkingDays(year : Int,month : Int) {

        viewModelScope.launch(Dispatchers.IO) { // Perform loading on the IO thread
            getNonWorkingDaysUseCase(year,month).collectLatest { resource ->
                when (resource) {
                    is UiResources.Loading -> withContext(Dispatchers.Main) {
                        _viewState.update { it.copy(isLoading = true) }
                    }

                    is UiResources.Success -> withContext(Dispatchers.Main) {
                        _viewState.update {

                            it.copy(
                                isLoading = false, nonWorkingDays = resource.data,
//                                nonWorkingDaysOnlyDays = nonWorkingDays.
                            )
//                           it.nonWorkingDaysOnlyDays.plus(it.nonWorkingDays[2].day)
                        }
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
 //                           loadHoliDaysOnlyDays(it)
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
    //----------------------------------------------------------------------
    // заполнение списка праздничных дней
    private fun loadHoliDaysOnlyDays(nonWorkingDaysViewState: NonWorkingDaysViewState) {

//        val mutableList = nonWorkingDaysViewState.holiDaysOnlyDays
//        nonWorkingDaysViewState.holiDaysOnlyDays=emptyList<Int>()

        nonWorkingDaysViewState.holiDays.forEach{
 //           mutableList.plus(  it.day)
            nonWorkingDaysViewState.holiDaysOnlyDays.plus(it.day)
        }
    }
    //----------------------------------------------------------------------
    // заполнение списка нерабочих дней
    private fun loadNonWorkingDaysOnlyDays(nonWorkingDaysViewState: NonWorkingDaysViewState) {

//        val mutableList = nonWorkingDaysViewState.nonWorkingDaysOnlyDays

        nonWorkingDaysViewState.nonWorkingDays.forEach{
 //           mutableList.plus(  it.day)
            nonWorkingDaysViewState.nonWorkingDaysOnlyDays.plus(it.day)
        }
    }
    //----------------------------------------------------------------------
}



