package com.childmathematics.android.shiftschedule.ui.schedules.schedule01

import android.util.Log
import androidx.lifecycle.ViewModel
import com.childmathematics.android.basement.lib.composecalendar.CalendarState
import com.childmathematics.android.basement.lib.composecalendar.DefaultCalendarPagerRange
import com.childmathematics.android.basement.lib.composecalendar.header.MonthState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionState
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.data.Item
import com.childmathematics.android.shiftschedule.data.ItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
class Schedule01PageViewModel(itemsRepository: ItemsRepository) : ViewModel() {

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

    private val _schedule01PageUiState = MutableStateFlow(Schedule01PageUiState())
    val schedule01PageUiState: StateFlow<Schedule01PageUiState> = _schedule01PageUiState.asStateFlow()

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    fun updateSelection(selection:List<LocalDate> ){
        Schedule01PageUiState.vmselection= selection.toList()

    }
//?    val vmstate: CalendarState<DynamicSelectionState> = CalendarState(
// monthState,
    /*
    MonthState(YearMonth.now(),
    YearMonth.now().minusMonths(DefaultCalendarPagerRange),
        YearMonth.now().plusMonths(DefaultCalendarPagerRange),
        ),
     */
// selectionState)
    /*
        DynamicSelectionState)

     */


}

/**
 * Ui State for HomeScreen
 * Состояние пользовательского интерфейса для HomeScreen
 */
//data class Schedule01PageUiState(val itemList: List<Item> = listOf())

data class Schedule01PageUiState(
var vmselection: List<LocalDate> = listOf()
//    var vmstate: DynamicSelectionState = SelectionState
/*
        CalendarState(

        MonthState(
            now(), now().minusMonths(
                DefaultCalendarPagerRange
            ), now().plusMonths(DefaultCalendarPagerRange)
        ), SelectionState
    )

 */

) {
    fun updateSelection(selection: List<LocalDate>) {
        Schedule01PageUiState.vmselection = selection.toList()
        if (BuildConfig.DEBUG) {
            //-------------------------
            for (i in vmselection.lastIndex downTo 0 step 1) {
                Log.d(
                    "Schedule01", "+++++VM: " + vmselection[i].dayOfMonth + "/"
                            + vmselection[i].monthValue + "/" + vmselection[i].year
                )
            }

        }
    }
    companion object {
        lateinit var vmselection: List<LocalDate>
    }
}


