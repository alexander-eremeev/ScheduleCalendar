package com.childmathematics.android.shiftschedule.ui.nonworkingdays

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.data.ItemsRepository
import com.childmathematics.android.shiftschedule.domain.repository.CountryRepository
import com.childmathematics.android.shiftschedule.domain.usecases.AddCountryUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.DeleteCountryUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.GetAllCountriesUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.GetCountryUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.SearchCountryUseCase
import com.childmathematics.android.shiftschedule.domain.usecases.UpdateCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    ): ViewModel() {
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
/*
//============================================================
@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val clearUsersUseCase: ClearUsersUseCase,
    private val searchUsersUseCase: SearchUsersUseCase,
    private val saveThemeUseCase: SaveThemeUseCase,
    private val getThemeUseCase: GetThemeUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow(UserViewState())
    val viewState: StateFlow<UserViewState> = _viewState
    private val _effectChannel = Channel<SnackbarEffect>()
    val effectFlow: Flow<SnackbarEffect> = _effectChannel.receiveAsFlow()
    private var recentlyDeletedUser: UserEntity? = null
    // Global Job variable to manage search cancellation only
    private var searchJob: Job? = null
    init {
    handleIntent(UserIntent.LoadTheme)
    handleIntent(UserIntent.LoadUsers)
    }
    fun handleIntent(intent: UserIntent) {
    when (intent) {
    is UserIntent.LoadUsers -> loadUsers()
    is UserIntent.AddUser -> validateAndAddUser(intent.name, intent.email, intent.imagePath)
    is UserIntent.DeleteUser -> deleteUser(intent.user)
    is UserIntent.ClearUsers -> clearUsers()
    is UserIntent.SearchUser -> searchUsers(intent.query)
    is UserIntent.UpdateName -> validateName(intent.name)
    is UserIntent.UpdateEmail -> validateEmail(intent.email)
    is UserIntent.UndoDelete -> undoDelete()
    is UserIntent.LoadTheme -> loadTheme()
    is UserIntent.UpdateTheme -> updateTheme(intent.isDarkTheme)
    }
}
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Load users from the Room database using Flow and UIResources
private fun loadUsers() {
    viewModelScope.launch(Dispatchers.IO) { // Perform loading on the IO thread
        getUsersUseCase().collectLatest { resource ->
            when (resource) {
                    is UIResources.Loading -> withContext(Dispatchers.Main) {
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
//===========================================
// Search users with job cancellation
private fun searchUsers(query: String) {
_viewState.update { it.copy(searchQuery = query) }
if (query.isEmpty()) {
// Reset to the full user list if the query is empty
_viewState.update {
it.copy(
filteredUsersList = it.users,
isLoading = false
)
}
} else {
// Cancel the previous job if one is active (to avoid multiple search requests)
searchJob?.cancel()
// Start a new search operation with SupervisorScope
searchJob = viewModelScope.launch(Dispatchers.IO) { // Perform search on the IO thread
searchUsersUseCase(query).collectLatest { resource ->
when (resource) {
is UIResources.Loading -> withContext(Dispatchers.Main) {
_viewState.update { it.copy(isLoading = true) }
}
is UIResources.Success -> withContext(Dispatchers.Main) {
_viewState.update {
it.copy(
isLoading = false,
filteredUsersList = resource.data
)
}
}
is UIResources.Error -> withContext(Dispatchers.Main) {
_viewState.update { it.copy(isLoading = false) }
_effectChannel.send(SnackbarEffect.ShowSnackbar("Error searching users:
${resource.message}"))
}
}
}
}
}
}
//===========================================
// Validate and add a new user (no job cancellation needed)
    private fun validateAndAddUser(name: String, email: String, imagePath: String?) {
    val nameError = !name.isValidName()
    val emailError = !email.isValidEmail()
    val imageError = !imagePath.isValidImage()
    // Check if the name, email, and image are valid
    if (nameError || emailError || imageError) {
    val errorMessage = buildErrorMessage(nameError, emailError, imageError)
    _viewState.update { it.copy(nameError = nameError, emailError = emailError) }
    viewModelScope.launch(Dispatchers.Main) {
    _effectChannel.send(SnackbarEffect.ShowSnackbar(errorMessage))
    }
    return
    }
    _viewState.update { it.copy(isLoading = true) }
    viewModelScope.launch(Dispatchers.IO) { // Perform database operations on the IO thread
    try {
    val imageUrl = imagePath // Save image to internal storage
    // Create a UserEntity and add it to the Room database
    addUserUseCase(UserEntity(name = name, email = email, imageUrl = imageUrl))
        withContext(Dispatchers.Main) {
            _viewState.update {
                it.copy(
                isLoading = false,
                name = "",
                email = "",
                nameError = false,
                emailError = false
                )
            }
            _effectChannel.send(SnackbarEffect.ShowSnackbar("User added successfully!"))
        }
            } catch (e: Exception) {
            withContext(Dispatchers.Main) {
            _viewState.update { it.copy(isLoading = false) }
            _effectChannel.send(SnackbarEffect.ShowSnackbar("Error adding user:
            ${e.message}"))
            }
            }
        }
    }
//===========================================
// Delete the selected user (no job cancellation needed)
    private fun deleteUser(user: UserEntity) {
        _viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) { // Perform delete operation on the IO thread
            recentlyDeletedUser = user
            try {
                deleteUserUseCase(user)
                withContext(Dispatchers.Main) {
                    _viewState.update { it.copy(isLoading = false) }
                    _effectChannel.send(SnackbarEffect.ShowSnackbar("User deleted", "Undo"))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _viewState.update { it.copy(isLoading = false) }
                    _effectChannel.send(SnackbarEffect.ShowSnackbar("Error deleting user:
                    ${e.message}"))
                }
            }
        }
    }
//===========================================
// Clear all users (no job cancellation needed)
    private fun clearUsers() {
        _viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                clearUsersUseCase()
                withContext(Dispatchers.Main) {
                    _viewState.update { it.copy(isLoading = false) }
                    _effectChannel.send(SnackbarEffect.ShowSnackbar("All users cleared!"))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _viewState.update { it.copy(isLoading = false) }
                    _effectChannel.send(SnackbarEffect.ShowSnackbar("Error clearing users:
                    ${e.message}"))
                }
            }
        }
    }
//===========================================
// Undo the last deleted user (no job cancellation needed)
    private fun undoDelete() {
        recentlyDeletedUser?.let { deletedUser ->
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    addUserUseCase(deletedUser)
                    withContext(Dispatchers.Main) {
                        _effectChannel.send(SnackbarEffect.ShowSnackbar("User restored
                        successfully!"))
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        _effectChannel.send(SnackbarEffect.ShowSnackbar("Error restoring user:
                        ${e.message}"))
                    }
                }
            }
        }
    }
//===========================================
    private fun validateName(name: String) {
        val nameError = !name.isValidName()
        _viewState.update { it.copy(name = name, nameError = nameError) }
    }
//===========================================
    private fun validateEmail(email: String) {
        val emailError = !email.isValidEmail()
        _viewState.update { it.copy(email = email, emailError = emailError) }
    }
//===========================================
    private fun buildErrorMessage(
        nameError: Boolean,
        emailError: Boolean,
        imageError: Boolean
        ): String {
            return when {
                nameError && emailError && imageError -> "Name, email, and image are required."
                nameError && emailError -> "Name and email are required."
                nameError -> "Name is required and must have at least 3 characters."
                emailError -> "Invalid email address."
                imageError -> "Please select or capture an image."
                else -> ""
            }
    }
//===========================================
    private fun loadTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            getThemeUseCase.invoke().collectLatest { isDarkTheme ->
                withContext(Dispatchers.Main) {
                   _viewState.update { it.copy(isDarkTheme = isDarkTheme) }
                }
            }
        }
    }
//===========================================
    private fun updateTheme(isDarkTheme: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            saveThemeUseCase.invoke(isDarkTheme)
            withContext(Dispatchers.Main) {
                _viewState.update { it.copy(isDarkTheme = isDarkTheme) }
            }
        }
    }
}
 */



