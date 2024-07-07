package com.childmathematics.android.shiftschedule.ui.schedules.schedule01

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childmathematics.android.basement.lib.composecalendar.CalendarState
import com.childmathematics.android.basement.lib.composecalendar.DefaultCalendarPagerRange
import com.childmathematics.android.basement.lib.composecalendar.header.MonthState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.EmptySelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionMode
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionState
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.data.Item
import com.childmathematics.android.shiftschedule.data.ItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.YearMonth.now

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
class Schedule01PageViewModel : ViewModel() {
//class Schedule01PageViewModel(itemsRepository: ItemsRepository) : ViewModel() {


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
/*
    private val _schedule01PageUiState = MutableStateFlow(Schedule01PageUiState())
    val schedule01PageUiState: StateFlow<Schedule01PageUiState> = _schedule01PageUiState.asStateFlow()

 */

//    var vmselection: List<LocalDate> = emptyList<LocalDate>()
//    var vmselection: List<LocalDate> = emptyList()
    var vmselection: List<LocalDate> by mutableStateOf(emptyList())

    /*
        companion object {
            private const val TIMEOUT_MILLIS = 5_000L
        }

     */
//    var vmselection: List<LocalDate> = emptyList<LocalDate>()

    /*
        companion object {
            var vmselection: List<LocalDate>
                get() {
                    TODO()
                }
        }

     */

    fun updateSelection(selection:List<LocalDate> ){
        /*
        viewModelScope.launch {
            _schedule01PageUiState.value.vmselection = selection.toList()
        }
        */
//        vmselection = selection.toList()
        vmselection = selection

        if (BuildConfig.DEBUG) {
            Log.d(
                "Schedule01", "+++updateSelectionVM: "+vmselection.lastIndex
            )
            for (i in vmselection.lastIndex downTo 0 step 1) {
                Log.d(
                    "Schedule01", "+++++VM: " + vmselection[i].dayOfMonth + "/"
                            + vmselection[i].monthValue + "/" + vmselection[i].year
                )
            }

            /*
            Log.d(
                "Schedule01", "+++updateSelectionVM: lastIndex= " +vmselection.lastIndex +
                        " + selection.lastIndex=" + selection.lastIndex
            )
            //-------------------------

            for (i in _schedule01PageUiState.value.vmselection.lastIndex downTo 0 step 1) {
                Log.d(
                    "Schedule01", "+++++VM: " + _schedule01PageUiState.value.vmselection[i].dayOfMonth + "/"
                            + _schedule01PageUiState.value.vmselection[i].monthValue + "/" + _schedule01PageUiState.value.vmselection[i].year
                )
            }

            for (i in vmselection.lastIndex downTo 0 step 1) {
                Log.d(
                    "Schedule01", "+++++VM: " + vmselection[i].dayOfMonth + "/"
                            + vmselection[i].monthValue + "/" + vmselection[i].year
                )
            }
            */
        }
    }


}

/**
 * Ui State for HomeScreen
 * Состояние пользовательского интерфейса для HomeScreen
 */
//data class Schedule01PageUiState(val itemList: List<Item> = listOf())
/*
data class Schedule01PageUiState(
val vmselection: List<LocalDate> = listOf()

) {
    fun updateSelection(selection: List<LocalDate>) {
        Schedule01PageUiState.vmselection = selection.toList()
        if (BuildConfig.DEBUG) {
            Log.d(
                "Schedule01", "+++updateSelectionVM: lastIndex= " + vmselection.lastIndex +
                        " + selection.lastIndex=" + selection.lastIndex
            )
            //-------------------------
            for (i in vmselection.lastIndex downTo 0 step 1) {
                Log.d(
                    "Schedule01", "+++++VM: " + vmselection[i].dayOfMonth + "/"
                            + vmselection[i].monthValue + "/" + vmselection[i].year
                )
            }

        }
    }


        val vmselection: List<LocalDate> = emptyList<LocalDate>()



}

 */
//val calendarState = CalendarState
/*
data class Schedule01PageUiState(

    var vmselection: List<LocalDate> = emptyList<LocalDate>()

)

 */




