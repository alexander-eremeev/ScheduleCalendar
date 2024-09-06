package com.childmathematics.android.shiftschedule.ui.in_app_update

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BackHand
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.childmathematics.android.basement.lib.in_app_update.InAppUpdateManager.UPDATEAVAILABLE
import com.childmathematics.android.basement.lib.in_app_update.InAppUpdateManager.appUpdateInfoTask
import com.childmathematics.android.basement.lib.in_app_update.InAppUpdateManager.appUpdateManager
import com.childmathematics.android.basement.lib.in_app_update.ScheduleAppUpdateManager
import com.childmathematics.android.shiftschedule.MainActivity
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.theme.ScheduleCalendarTheme
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun InAppUpdatePageScreen(
fun InAppUpdatePageScreen(
        modifier: Modifier = Modifier,
        onBackClick: () -> Unit,
        updateViewModel:UpdateViewModel
    ) {
    val DAYS_FOR_FLEXIBLE_UPDATE:Int = 5
    var mes: Boolean
    //=================================
//    val updateViewModel:UpdateViewModel by viewModel()
    val updateUiState by updateViewModel.updateUiState.collectAsStateWithLifecycle()
//    val updateUiState by updateViewModel.updateUiState.collectAsState()

    val scope = rememberCoroutineScope()

    //=================================

    ScheduleCalendarTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        Scaffold(
            modifier = modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                InAppUpdatePageTopAppBar(
                    onBackClick, scrollBehavior,
                )
            },
            content ={ padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
//                   updateViewModel.checkForUpdateApp()
                    Text( text = "+++++++++++++++++++++++++++++"
                    )
                    for (i in  updateUiState.lastIndex downTo 0 step 1) {
                        Text( text = updateUiState[updateUiState.lastIndex].message)
                    }
                    Text( text = "==================================="
                    )
                }
            },
        )
    }
}

//=================================
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun InAppUpdatePageTopAppBar(
                onBackClick: () -> Unit,
                scrollBehavior: TopAppBarScrollBehavior,
) {
    val text1: String
    if(UPDATEAVAILABLE) text1 = stringResource(id = R.string.UpdatePage)
    else text1 = stringResource(id = R.string.inAppUpdatePage)
    CenterAlignedTopAppBar(
        title = {
            Text(text = text1)
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Filled.BackHand, stringResource(id = R.string.back_button))
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = Modifier.fillMaxWidth()
    )
}
//+++++++++++++++++++++++++++++++++++++++++++++++++
/**
 *    Types of UpdateAvailability:
 *     -> UpdateAvailability.UNKNOWN
 *     -> UpdateAvailability.UPDATE_NOT_AVAILABLE
 *     -> UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
 *     -> UpdateAvailability.UPDATE_AVAILABLE
 */
//var appUpdateInfo: AppUpdateInfo? = null
//val revealPIN by viewModel.isShowingPin.collectAsStateWithLifecycle()
/*
data class UpdateUiState(
    val ListUpdateInfoSucces:Boolean,
    val UpdateAvailabilityStatus: Int = 999,
    val message: String = ""
)
class UpdateViewModel: ViewModel() {
    private val _updateState = MutableStateFlow<List<UpdateUiState>>(emptyList())
    val updateUiState: StateFlow<List<UpdateUiState>> = _updateState.asStateFlow()

    var message: String = "__"

    /*
    init {
        _updateState.value =
    }

     */

    @Composable
    fun checkForUpdateApp() {

        val scope = rememberCoroutineScope()
        val  appUpdateManager = AppUpdateManagerFactory.create(LocalContext.current)
        // Returns an intent object that you use to check for an update.
        // Возвращает объект намерения, который вы используете для проверки наличия обновления.
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        // Checks that the platform will allow the specified type of update.
        // Проверяет, разрешит ли платформа указанный тип обновления.
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++
        appUpdateInfoTask.addOnCompleteListener {
            if (it.isSuccessful) {
                appUpdateInfo = it.result
                when (appUpdateInfo?.updateAvailability()) {
                    UpdateAvailability.UNKNOWN -> {
                        _updateState.value += UpdateUiState(
                            ListUpdateInfoSucces=true,
                            UpdateAvailabilityStatus = UpdateAvailability.UNKNOWN,
                            message= "InAppUpdatePageScreen "+" checkForUpdate: isAvailable=false"
                                    + " Unknown Response "
                        )
                        message= "InAppUpdatePageScreen "+" checkForUpdate: isAvailable=false"+
                                " Unknown Response "
                        message= UpdateAvailability.UNKNOWN.toString()+
                                " Unknown Response "

                    }
                    UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                        _updateState.value += UpdateUiState(
                            ListUpdateInfoSucces=true,
                            UpdateAvailabilityStatus = UpdateAvailability.UPDATE_NOT_AVAILABLE,
                            message= "InAppUpdatePageScreen "+" checkForUpdate: isAvailable=false"
                                    + " No Updates Available"
                        )
                        message= "InAppUpdatePageScreen "+" checkForUpdate: isAvailable=false"+
                                " No Updates Available"
                        message= UpdateAvailability.UPDATE_NOT_AVAILABLE.toString()+
                                " No Updates Available"
                    }
                    UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                        _updateState.value += UpdateUiState(
                            ListUpdateInfoSucces=true,
                            UpdateAvailabilityStatus =
                            UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS ,
                            message= "InAppUpdatePageScreen "
                                    +" checkForUpdate: isAvailable=false " +
                                    " Update is in Progress. Please Wait"
                        )
                        message= "InAppUpdatePageScreen "+" checkForUpdate: isAvailable=false " +
                                " Update is in Progress. Please Wait"

                    }
                }
            } else {
                _updateState.value += UpdateUiState(
                    ListUpdateInfoSucces=false,
                    UpdateAvailabilityStatus = UpdateAvailability.UNKNOWN ,
                    message= "InAppUpdatePageScreen "+" checkForUpdate: error="
                            +it.exception?.message.toString()
                )
                message= "InAppUpdatePageScreen "+" checkForUpdate: error="+
                        it.exception?.message.toString()
            }
        }
        */
//=================================================
/*
        Column {
            Button(
                onClick = {
                    scope.launch {
                        try {
                            appUpdateManager.appUpdateInfo.addOnCompleteListener {
                                if (it.isSuccessful) {
                                    appUpdateInfo = it.result
                                    when (appUpdateInfo?.updateAvailability()) {
                                        UpdateAvailability.UNKNOWN -> {
                                            _updateState.value += UpdateUiState(
                                                ListUpdateInfoSucces=true,
                                                UpdateAvailabilityStatus = UpdateAvailability.UNKNOWN,
                                                message= "InAppUpdatePageScreen "+" checkForUpdate: isAvailable=false"
                                                        + " Unknown Response "
                                            )
                                            message= "InAppUpdatePageScreen "+" checkForUpdate: isAvailable=false"+
                                                    " Unknown Response "
                                            message= UpdateAvailability.UNKNOWN.toString()+
                                                    " Unknown Response "

                                        }
                                        UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                                            _updateState.value += UpdateUiState(
                                                ListUpdateInfoSucces=true,
                                                UpdateAvailabilityStatus = UpdateAvailability.UPDATE_NOT_AVAILABLE,
                                                message= "InAppUpdatePageScreen "+" checkForUpdate: isAvailable=false"
                                                        + " No Updates Available"
                                            )
                                            message= "InAppUpdatePageScreen "+" checkForUpdate: isAvailable=false"+
                                                    " No Updates Available"
                                            message= UpdateAvailability.UPDATE_NOT_AVAILABLE.toString()+
                                                    " No Updates Available"
                                         }
                                        UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                                            _updateState.value += UpdateUiState(
                                                ListUpdateInfoSucces=true,
                                                UpdateAvailabilityStatus =
                                                    UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS ,
                                                message= "InAppUpdatePageScreen "
                                                        +" checkForUpdate: isAvailable=false " +
                                                        " Update is in Progress. Please Wait"
                                            )
                                            message= "InAppUpdatePageScreen "+" checkForUpdate: isAvailable=false " +
                                                    " Update is in Progress. Please Wait"

                                        }
                                    }

                                } else {
                                    _updateState.value += UpdateUiState(
                                        ListUpdateInfoSucces=false,
                                        UpdateAvailabilityStatus = UpdateAvailability.UNKNOWN ,
                                        message= "InAppUpdatePageScreen "+" checkForUpdate: error="
                                                +it.exception?.message.toString()
                                    )
                                    message= "InAppUpdatePageScreen "+" checkForUpdate: error="+
                                            it.exception?.message.toString()

                                }
                            }
                            //-----------------------------------
                            delay(30000)
                        } catch (ioe: IOException) {
                            _updateState.value += UpdateUiState(
                                ListUpdateInfoSucces=false,
                                UpdateAvailabilityStatus = UpdateAvailability.UNKNOWN ,
                                message= "InAppUpdatePageScreen "+" checkForUpdate: IOE error="
                                        +ioe.message.toString()
                            )
                            message= "InAppUpdatePageScreen "+" checkForUpdate: IOE error="+ioe.message.toString()

                        }
                    }
                }
            ) {
                Text("???$message")
//                Text("--------------" + updateUiState[updateUiState.lastIndex].message+"--------------")
      //          Text(_updateState.value)
            }
            /*
            if (_updateState.UpdateAvailabilityStatus ==UPDATE_AVAILABLE) {
                Text(text = "Your PIN is 1234")
            }

             */
        }

 */
            //------------------------
 //   }
/*
        appUpdateManager.appUpdateInfo.addOnCompleteListener {
            if (it.isSuccessful) {
                appUpdateInfo = it.result
                when (appUpdateInfo?.updateAvailability()) {
                    UpdateAvailability.UNKNOWN -> {
                        callback.invoke(false, "Unknown Response")
//;                        UpdateAvailability.UNKNOWN -> {callback.invoke(false, "Unknown Response")
                        Log.d(
                            "ScheduleAppUpdateManager",
                            "checkForUpdate: isAvailable=false" + " Unknown Response "
                        )
//                    messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=false"+" Unknown Response"
                    }
//                    UpdateAvailability.UPDATE_NOT_AVAILABLE -> callback.invoke(false, "No Updates Available")
                    UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                        callback.invoke(false, "No Updates Available")
                        Log.d(
                            "ScheduleAppUpdateManager",
                            "checkForUpdate: isAvailable=false" + " No Updates Available"
                        )
//                    messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=false"+
                        " No Updates Available"
                    }
//                    UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> callback.invoke(false,
//                    "Update is in Progress. Please Wait")
                    UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                        callback.invoke(false, "Update is in Progress. Please Wait")
                        Log.d(
                            "ScheduleAppUpdateManager", "checkForUpdate: isAvailable=false" +
                                    " Update is in Progress. Please Wait"
                        )
//                    messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=false"+
                        " Update is in Progress. Please Wait"

                    }

                    UpdateAvailability.UPDATE_AVAILABLE -> {
                        if (appUpdateInfo?.isUpdateTypeAllowed(updateType) == true) {
                            callback.invoke(true, "Update Available")
                            Log.d(
                                "ScheduleAppUpdateManager",
                                "checkForUpdate: isAvailable=true" + " Update Available"
                            )
//                        messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=true"+
                            " Update Available"
                        } else {
                            callback.invoke(false, "This type of update is not available.")
                            Log.d(
                                "ScheduleAppUpdateManager", "checkForUpdate: isAvailable=false" +
                                        " This type of update is not available."
                            )
//                        messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=false"+
                            " This type of update is not available."
                        }
                    }
                }
            } else {
                callback.invoke(false, it.exception?.message.toString())
                Log.d(
                    "ScheduleAppUpdateManager",
                    "checkForUpdate: error " + it.exception?.message.toString()
                )
//            messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: error="+it.exception?.message.toString()
            }
        }
    }

 */
//}
//--------------------------------------------------------------
//=====================
