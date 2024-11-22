package com.childmathematics.android.shiftschedule.ui.inappupdate

import android.app.Activity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childmathematics.android.basement.lib.in_app_update.InAppUpdateManager.appUpdateManager
import com.childmathematics.android.shiftschedule.ui.inappupdate.InAppUpdateMan.UPDATEAVAILABLE
import com.childmathematics.android.shiftschedule.ui.inappupdate.InAppUpdateMan.activityResultLauncher
import com.childmathematics.android.shiftschedule.ui.inappupdate.InAppUpdateMan.appUpdateInfo
import com.childmathematics.android.shiftschedule.ui.inappupdate.InAppUpdateMan.appUpdateInfoTask
import com.childmathematics.android.shiftschedule.ui.inappupdate.InAppUpdateMan.appUpdateMan
import com.google.android.gms.tasks.Task
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
object InAppUpdateMan {

    //   private lateinit var appUpdateManager: AppUpdateManager
    lateinit var appUpdateMan: AppUpdateManager
    lateinit var appUpdateInfoTask: Task<AppUpdateInfo>
    var appUpdateInfo: AppUpdateInfo? = null

    //Launcher для заранее подготовленного вызова для начала процесса выполнения ActivityResultContract,
    // который принимает I в качестве обязательных входных данных.
//    private lateinit var activityResultLauncher: ActivityResultLauncher<IntentSenderRequest>
    lateinit var activityResultLauncher: ActivityResultLauncher<IntentSenderRequest>
    var UPDATEAVAILABLE:Boolean = false
    var availVersionCode:Int = 1
    var packName:String ="Init"
    /**
     * Initializes the In-App Update Manager.
     *
     * @param activity The activity context used to create the AppUpdateManager.
     * @param launcher The ActivityResultLauncher to handle the update flow result.
     *
     * Call this function to initialize the update manager which will check for updates.
     */

    fun init(activity: Activity,launcher: ActivityResultLauncher<IntentSenderRequest>) {
        appUpdateMan = AppUpdateManagerFactory.create(activity)
        activityResultLauncher = launcher
        //    checkForUpdates()
        UPDATEAVAILABLE = false
    }

}
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

        var appUpdateInfo: AppUpdateInfo? = null
        val appUpdateManager = AppUpdateManagerFactory.create(LocalContext.current)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        // Returns an intent object that you use to check for an update.
        // Возвращает объект намерения, который вы используете для проверки наличия обновления.
        // Checks that the platform will allow the specified type of update.
        // Проверяет, разрешит ли платформа указанный тип обновления.
//-----------------
//        viewModelScope.launch(Dispatchers.IO) {
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
                         if (appUpdateInfo?.isUpdateTypeAllowed(updateType) == true) {
//                            if (appUpdateInfo.isUpdateTypeAllowed(updateType) == true) {
                             UPDATEAVAILABLE = true
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
            }.addOnFailureListener {
                    // Handle failure here
            _updateState.value += UpdateUiState(
                listUpdateInfoSuccess = false,
                updateAvailabilityStatus = UpdateAvailability.UNKNOWN,
                message = "UpdateViewModel " + " checkForUpdateApp: error="
                        + it.message.toString()
            )

        }
//        }
            //--------------------
    }
    fun CheckForUpdatedApp() {
//        fun CheckForUpdatedApp(activity: ComponentActivity) {
//        suspend fun CheckForUpdatedApp(activity: MainActivity) {
//        val activity: MainActivity
 //       var appUpdateInfo: AppUpdateInfo? = null
        //activity

//        val appUpdateManager = AppUpdateManagerFactory.create(activity)
//        val appUpdateManager = AppUpdateManagerFactory.create(updateActionListener.getActivity())
        val appUpdateInfoTask = appUpdateMan.appUpdateInfo
        // Returns an intent object that you use to check for an update.
        // Возвращает объект намерения, который вы используете для проверки наличия обновления.
        // Checks that the platform will allow the specified type of update.
        // Проверяет, разрешит ли платформа указанный тип обновления.
//-----------------
        viewModelScope.launch(Dispatchers.IO) {
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
                            if (appUpdateInfo?.isUpdateTypeAllowed(updateType) == true) {
    //                            if (appUpdateInfo.isUpdateTypeAllowed(updateType) == true) {
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
            }.addOnFailureListener{
                // Handle failure here
                _updateState.value += UpdateUiState(
                    listUpdateInfoSuccess = false,
                    updateAvailabilityStatus = UpdateAvailability.UNKNOWN,
                    message = "UpdateViewModel " + " checkForUpdateApp: error="
                            + it.message.toString()
                )

            }
        }
        //--------------------
    }
    fun startUpdateApp(appUpdateInfo: AppUpdateInfo){

        appUpdateMan.startUpdateFlowForResult(
// Pass the intent that is returned by 'getAppUpdateInfo()'.
            appUpdateInfo,
// an activity result launcher registered via registerForActivityResult
            activityResultLauncher,
// Or pass 'AppUpdateType.FLEXIBLE' to newBuilder() for
// flexible updates.
            AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build())
    }

    fun CheckAndUpdatedApp() {
//        fun CheckForUpdatedApp(activity: ComponentActivity) {
//        suspend fun CheckForUpdatedApp(activity: MainActivity) {
//        val activity: MainActivity
        //       var appUpdateInfo: AppUpdateInfo? = null
        //activity

//        val appUpdateManager = AppUpdateManagerFactory.create(activity)
//        val appUpdateManager = AppUpdateManagerFactory.create(updateActionListener.getActivity())
        val appUpdateInfoTask = appUpdateMan.appUpdateInfo
        // Returns an intent object that you use to check for an update.
        // Возвращает объект намерения, который вы используете для проверки наличия обновления.
        // Checks that the platform will allow the specified type of update.
        // Проверяет, разрешит ли платформа указанный тип обновления.
//-----------------
        viewModelScope.launch(Dispatchers.IO) {
            appUpdateInfoTask.addOnCompleteListener {
                if (it.isSuccessful) {

                    appUpdateInfo = it.result
                    when (appUpdateInfo?.updateAvailability()) {

                        UpdateAvailability.UPDATE_AVAILABLE -> {
                            if (appUpdateInfo?.isUpdateTypeAllowed(updateType) == true) {
                                startUpdateApp(appUpdateInfo!!)
                            }
                        }
                    }
                }
            }
        }
        //--------------------
    }
}
