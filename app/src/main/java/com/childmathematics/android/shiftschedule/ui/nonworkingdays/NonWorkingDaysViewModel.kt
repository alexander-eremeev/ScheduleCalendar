package com.childmathematics.android.shiftschedule.ui.nonworkingdays

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
class NonWorkingDaysViewModel : ViewModel() {
//class NonWorkingDaysViewModel(itemsRepository: ItemsRepository) : ViewModel() {


    /**
     * Holds home ui state. The list of items are retrieved from [ItemsRepository] and mapped to
     * [HomeUiState]
     * Сохраняет исходное состояние пользовательского интерфейса. Список элементов извлекается
     * из [ItemsRepository] и сопоставляется с [HomeUiState]
     */
    /*
    val nonWorkingDays01PageUiState: StateFlow<NonWorkingDays01PageUiState> =
        itemsRepository.getAllItemsStream().map { NonWorkingDays01PageUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = NonWorkingDays01PageUiState()
            )
     */

    private val _nonWorkingDaysUiState = MutableStateFlow<List<LocalDate>>(emptyList())
    val nonWorkingDaysUiState: StateFlow<List<LocalDate>> = _nonWorkingDaysUiState.asStateFlow()
    init {
        _nonWorkingDaysUiState.value = NonWorkingDaysUiState.suvmselection
    }
    fun emptySelection() {
        _nonWorkingDaysUiState.value = emptyList()
    }
    fun updateSelection(selection:List<LocalDate> ){
        _nonWorkingDaysUiState.value = selection

        if (BuildConfig.DEBUG) {
            /*
            Log.d(
//                "NonWorkingDays01", "+++updateSelectionVM: "+svmselection.lastIndex
                "NonWorkingDaysViewModel", "+++updateSelectionVM: "+_nonWorkingDaysUiState.value.lastIndex
            )

             */
            for (i in _nonWorkingDaysUiState.value.lastIndex downTo 0 step 1) {
                Log.d(
                    "NonWorkingDaysViewModel", "+++++VM: " + _nonWorkingDaysUiState.value[i].dayOfMonth + "/"
                            + _nonWorkingDaysUiState.value[i].monthValue + "/" + _nonWorkingDaysUiState.value[i].year
                )
            }
        }
    }
}

/**
 * Ui State for HomeScreen
 * Состояние пользовательского интерфейса для HomeScreen
 */
data class NonWorkingDaysUiState(
    val suvmselection: List<LocalDate> = listOf()
) {
    companion object {
        var suvmselection: List<LocalDate> by mutableStateOf(emptyList())
    }
}




