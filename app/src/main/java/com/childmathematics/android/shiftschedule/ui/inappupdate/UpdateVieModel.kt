package com.childmathematics.android.shiftschedule.ui.inappupdate

import android.R.attr.delay
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay

private var updateType = AppUpdateType.IMMEDIATE

data class UpdateUiState(
    val listUpdateInfoSuccess:Boolean,
    val updateAvailabilityStatus: Int = 999,
    val message: String = ""
)
class UpdateViewModel: ViewModel() {
    private val _updateState = MutableStateFlow<List<UpdateUiState>>(emptyList())
    val updateUiState: StateFlow<List<UpdateUiState>> = _updateState.asStateFlow()


    @Composable
    fun CheckForUpdateApp() {

        val appUpdateManager = AppUpdateManagerFactory.create(LocalContext.current)
        var appUpdateInfo: AppUpdateInfo? = null
        // Returns an intent object that you use to check for an update.
        // Возвращает объект намерения, который вы используете для проверки наличия обновления.
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        // Checks that the platform will allow the specified type of update.
        // Проверяет, разрешит ли платформа указанный тип обновления.
//        viewModelScope.launch(Dispatchers.IO) {
//-----------------
            appUpdateInfoTask.addOnCompleteListener {
                if (it.isSuccessful) {
                    appUpdateInfo = it.result
                    when (appUpdateInfo?.updateAvailability()) {
                        UpdateAvailability.UNKNOWN -> {
                            _updateState.value += UpdateUiState(
                                listUpdateInfoSuccess = true,
                                updateAvailabilityStatus = UpdateAvailability.UNKNOWN,
                                message = "UpdateViewModel" + " checkForUpdateApp: Unknown Response "
                            )
                        }

                        UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                            _updateState.value += UpdateUiState(
                                listUpdateInfoSuccess = true,
                                updateAvailabilityStatus = UpdateAvailability.UPDATE_NOT_AVAILABLE,
                                message = "UpdateViewModel " + " checkForUpdatAppe: "
                                        + " No Updates Available"
                            )
                        }

                        UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                            _updateState.value += UpdateUiState(
                                listUpdateInfoSuccess = true,
                                updateAvailabilityStatus =
                                UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS,
                                message = "UpdateViewModel "
                                        + " checkForUpdateApp: " +
                                        " Update is in Progress. Please Wait"
                            )
                        }

                        UpdateAvailability.UPDATE_AVAILABLE -> {
//                         if (appUpdateInfo?.isUpdateTypeAllowed(updateType) == true) {
                            if (appUpdateInfo.isUpdateTypeAllowed(updateType) == true) {
                                _updateState.value += UpdateUiState(
                                    listUpdateInfoSuccess = true,
                                    updateAvailabilityStatus =
                                    UpdateAvailability.UPDATE_AVAILABLE,
                                    message = "UpdateViewModel "
                                            + " checkForUpdateApp: " +
                                            " Update Available"
                                )
                            }
                        }
                    }
                } else {
                    _updateState.value += UpdateUiState(
                        listUpdateInfoSuccess = false,
                        updateAvailabilityStatus = UpdateAvailability.UNKNOWN,
                        message = "UpdateViewModel " + " checkForUpdateApp: error="
                                + it.exception?.message.toString()
                    )
                }
            }
            //--------------------
//        }
    }
}