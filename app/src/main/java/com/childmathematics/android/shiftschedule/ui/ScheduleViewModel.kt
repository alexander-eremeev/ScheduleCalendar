package com.childmathematics.android.shiftschedule.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.data.ItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

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
class ScheduleViewModel : ViewModel() {
//class ScheduleViewModel(itemsRepository: ItemsRepository) : ViewModel() {


    /**
     * Holds home ui state. The list of items are retrieved from [ItemsRepository] and mapped to
     * [HomeUiState]
     * Сохраняет исходное состояние пользовательского интерфейса. Список элементов извлекается
     * из [ItemsRepository] и сопоставляется с [HomeUiState]
     */
    /*
    val schedule01PageUiState: StateFlow<Schedule01PageUiState> =
        itemsRepository.getAllItemsStream().map { Schedule01PageUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = Schedule01PageUiState()
            )
     */

    private val _scheduleUiState = MutableStateFlow<List<LocalDate>>(emptyList())
    val scheduleUiState: StateFlow<List<LocalDate>> = _scheduleUiState.asStateFlow()
    init {
        _scheduleUiState.value = ScheduleUiState.suvmselection
    }
    fun emptySelection() {
        _scheduleUiState.value = emptyList()
    }
    fun updateSelection(selection:List<LocalDate> ){
         _scheduleUiState.value = selection

        if (BuildConfig.DEBUG) {
            /*
            Log.d(
//                "Schedule01", "+++updateSelectionVM: "+svmselection.lastIndex
                "ScheduleViewModel", "+++updateSelectionVM: "+_scheduleUiState.value.lastIndex
            )

             */
            for (i in _scheduleUiState.value.lastIndex downTo 0 step 1) {
                Log.d(
                    "ScheduleViewModel", "+++++VM: " + _scheduleUiState.value[i].dayOfMonth + "/"
                            + _scheduleUiState.value[i].monthValue + "/" + _scheduleUiState.value[i].year
                )
            }
        }
    }
}

/**
 * Ui State for HomeScreen
 * Состояние пользовательского интерфейса для HomeScreen
 */
data class ScheduleUiState(
val suvmselection: List<LocalDate> = listOf()
) {
    companion object {
        var suvmselection: List<LocalDate> by mutableStateOf(emptyList())
    }
}




