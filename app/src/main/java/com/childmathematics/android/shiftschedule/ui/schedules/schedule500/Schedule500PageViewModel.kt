package com.childmathematics.android.shiftschedule.ui.schedules.schedule500

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childmathematics.android.shiftschedule.data.Item
import com.childmathematics.android.shiftschedule.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

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
class Schedule500PageViewModel(itemsRepository: ItemsRepository) : ViewModel() {

    /**
     * Holds home ui state. The list of items are retrieved from [ItemsRepository] and mapped to
     * [HomeUiState]
     * Сохраняет исходное состояние пользовательского интерфейса. Список элементов извлекается
     * из [ItemsRepository] и сопоставляется с [HomeUiState]
     */
    val schedule500PageUiState: StateFlow<Schedule500PageUiState> =
        itemsRepository.getAllItemsStream().map { Schedule500PageUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = Schedule500PageUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 * Состояние пользовательского интерфейса для HomeScreen
 */
data class Schedule500PageUiState(val itemList: List<Item> = listOf())
