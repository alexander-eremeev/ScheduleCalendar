package com.childmathematics.android.shiftschedule.ui.in_app_update

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.app.ProgressDialog.show
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsProperties.ProgressBarRangeInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import com.childmathematics.android.basement.lib.in_app_update.InAppUpdateManager
import com.childmathematics.android.basement.lib.in_app_update.InAppUpdateManager.UPDATEAVAILABLE
import com.childmathematics.android.basement.lib.in_app_update.InAppUpdateManager.activityResultLauncher
import com.childmathematics.android.basement.lib.in_app_update.InAppUpdateManager.appUpdateManager
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.theme.ScheduleCalendarTheme
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InAppUpdatePageScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    updateViewModel: UpdateViewModel,
    )
{
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
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {

                    Button(
                        onClick = {  },
                    )
                    {
                        Text(stringResource(id = R.string.UpdatePage),
                            fontWeight = FontWeight.Bold,
                        )
                     }

                    var appInstalled: Boolean = false

                    val appUpdateManager = AppUpdateManagerFactory.create(LocalContext.current)
                    // Returns an intent object that you use to check for an update.
                    // Возвращает объект намерения, который вы используете для проверки наличия обновления.
                    val appUpdateInfoTask = appUpdateManager.appUpdateInfo
                    // Checks that the platform will allow the specified type of update.
                    // Проверяет, разрешит ли платформа указанный тип обновления.
                    /*
                    val listener:InstallStateUpdatedListener
                    appUpdateManager.registerListener(listener)
                    */
 //                   lateinit var launcher: ActivityResultLauncher<IntentSenderRequest>


                    lateinit var activityResultLauncher: ActivityResultLauncher<IntentSenderRequest>

                    appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
                        if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                            // This example applies an immediate update. To apply a flexible update
                            // instead, pass in AppUpdateType.FLEXIBLE
                            // Этот пример применяет немедленное обновление. Чтобы применить гибкое обновление
                            // вместо этого передайте AppUpdateType.FLEXIBLE
                            && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
                        ) {
                            // Request the update.
                            // Запросить обновление.
                            //---------------------------------------------
                            // Create a listener to track request state updates.
                            // Before starting an update, register a listener for updates.

                            val listener = InstallStateUpdatedListener { state ->
                                // (Optional) Provide a download progress bar.
                                if (state.installStatus() == InstallStatus.DOWNLOADING) {
                                    appInstalled = false

                                    val bytesDownloaded = state.bytesDownloaded()
                                    val totalBytesToDownload = state.totalBytesToDownload()
                                    // Show update progress bar.
                                    /*
                                    ProgressBarRangeInfo(
                                        current= bytesDownloaded.toFloat(),
                                        range = totalBytesToDownload.toFloat(),
                                   // steps = 0
                                    )
                                    
                                     */
//                                        Text("")

                                }
                                if (state.installStatus() == InstallStatus.DOWNLOADED) {
                                    // After the update is downloaded, show a notification
                                    // and request user confirmation to restart the app.
                                    // После загрузки обновления покажите уведомление
                                    // и запросите подтверждение пользователя на перезапуск приложения.
                                    // +++++++
                                }
                                if (state.installStatus() == InstallStatus.INSTALLED) {
                                    appInstalled = true
                                }
                                 //-----------------------------------------

                            }


                            appUpdateManager.registerListener(listener)

                            appUpdateManager.startUpdateFlowForResult(
                                // Pass the intent that is returned by 'getAppUpdateInfo()'.
                                appUpdateInfo,
                                // an activity result launcher registered via registerForActivityResult
                                activityResultLauncher,
                                // Or pass 'AppUpdateType.FLEXIBLE' to newBuilder() for
                                // flexible updates.
                                AppUpdateOptions.newBuilder(AppUpdateType.FLEXIBLE)
                                    .build()
                            )
                            //_____________________________________
                            if (appInstalled)appUpdateManager.unregisterListener(listener)


                            //_____________________________________

                        }
                    }
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
//------------------------------------
/*
// Запуск
appUpdateManager.startUpdateFlowForResult(
// Pass the intent that is returned by 'getAppUpdateInfo()'.
appUpdateInfo,
// an activity result launcher registered via registerForActivityResult
activityResultLauncher,
// Or pass 'AppUpdateType.FLEXIBLE' to newBuilder() for
// flexible updates.
AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build())
//--------------------------------
// Before starting an update, register a listener for updates.
appUpdateManager.registerListener(listenerUp)
// Start an update.

//--------------------------------------
// Create a listener to track request state updates.
val listenerUp = InstallStateUpdatedListener { state ->
// (Optional) Provide a download progress bar.
    if (state.installStatus() == InstallStatus.DOWNLOADING) {
        val bytesDownloaded = state.bytesDownloaded()
        val totalBytesToDownload = state.totalBytesToDownload()
// Show update progress bar.
    }
// Log state or install the update.
}
// Before starting an update, register a listener for updates.
appUpdateManager.registerListener(listenerUp)
// Start an update.
// When status updates are no longer needed, unregister the listener.
appUpdateManager.unregisterListener(listenerUp)

 */
//------------------------------------------
/*
val listener = { state ->
    if (state.installStatus() == InstallStatus.DOWNLOADED) {
// After the update is downloaded, show a notification
// and request user confirmation to restart the app.
        popupSnackbarForCompleteUpdate()
    }
    ...
}

 */
//-------------------------------------------
// Displays the snackbar notification and call to action.
/*
fun popupSnackbarForCompleteUpdate() {
    Snackbar.make(
        findViewById(R.id.activity_main_layout),
        "An update has just been downloaded.",
        Snackbar.LENGTH_INDEFINITE
    ).apply {
        setAction("RESTART") { appUpdateManager.completeUpdate() }
        setActionTextColor(resources.getColor(R.color.snackbar_action_text_color))
        show()
    }
}

 */
//++++++++++++++++++++++++++++++++++++++