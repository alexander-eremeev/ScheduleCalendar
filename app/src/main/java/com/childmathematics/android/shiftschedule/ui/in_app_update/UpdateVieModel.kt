package com.childmathematics.android.shiftschedule.ui.in_app_update

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private var updateType = AppUpdateType.IMMEDIATE

data class UpdateUiState(
    val ListUpdateInfoSucces:Boolean,
    val UpdateAvailabilityStatus: Int = 999,
    val message: String = ""
)
class UpdateViewModel: ViewModel() {
    private val _updateState = MutableStateFlow<List<UpdateUiState>>(emptyList())
    val updateUiState: StateFlow<List<UpdateUiState>> = _updateState.asStateFlow()


    @Composable
    fun checkForUpdateApp() {

        val appUpdateManager = AppUpdateManagerFactory.create(LocalContext.current)
        var appUpdateInfo: AppUpdateInfo? = null
        // Returns an intent object that you use to check for an update.
        // Возвращает объект намерения, который вы используете для проверки наличия обновления.
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        // Checks that the platform will allow the specified type of update.
        // Проверяет, разрешит ли платформа указанный тип обновления.

        appUpdateInfoTask.addOnCompleteListener {
            if (it.isSuccessful) {
                appUpdateInfo = it.result
                when (appUpdateInfo?.updateAvailability()) {
                    UpdateAvailability.UNKNOWN -> {
                        _updateState.value += UpdateUiState(
                            ListUpdateInfoSucces = true,
                            UpdateAvailabilityStatus = UpdateAvailability.UNKNOWN,
                            message = "UpdateViewModel" + " checkForUpdateApp: Unknown Response "
                        )
                     }
                    UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                        _updateState.value += UpdateUiState(
                            ListUpdateInfoSucces = true,
                            UpdateAvailabilityStatus = UpdateAvailability.UPDATE_NOT_AVAILABLE,
                            message = "UpdateViewModel " + " checkForUpdatAppe: "
                                    + " No Updates Available"
                        )
                    }

                    UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                        _updateState.value += UpdateUiState(
                            ListUpdateInfoSucces = true,
                            UpdateAvailabilityStatus =
                              UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS,
                            message = "UpdateViewModel "
                                    + " checkForUpdateApp: " +
                                    " Update is in Progress. Please Wait"
                        )
                    }
                     UpdateAvailability.UPDATE_AVAILABLE -> {
                        if (appUpdateInfo?.isUpdateTypeAllowed(updateType) == true) {
                            _updateState.value += UpdateUiState(
                                ListUpdateInfoSucces = true,
                                UpdateAvailabilityStatus =
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
                    ListUpdateInfoSucces = false,
                    UpdateAvailabilityStatus = UpdateAvailability.UNKNOWN,
                    message = "UpdateViewModel " + " checkForUpdateApp: error="
                            + it.exception?.message.toString()
                )
            }
        }
    }
}