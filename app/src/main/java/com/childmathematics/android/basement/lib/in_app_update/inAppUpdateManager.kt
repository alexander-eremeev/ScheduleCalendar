package com.childmathematics.android.basement.lib.in_app_update

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.childmathematics.android.shiftschedule.BuildConfig
import com.google.android.gms.tasks.Task
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import kotlin.properties.Delegates

object InAppUpdateManager {

 //   private lateinit var appUpdateManager: AppUpdateManager
    lateinit var appUpdateManager: AppUpdateManager
    lateinit var appUpdateInfoTask: Task<AppUpdateInfo>

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

    fun init(activity: Activity, launcher: ActivityResultLauncher<IntentSenderRequest>) {
        appUpdateManager = AppUpdateManagerFactory.create(activity)
        activityResultLauncher = launcher
    //    checkForUpdates()
    }
    @Composable
    fun initCheckForUpdates() {
        val context = LocalContext.current
        appUpdateManager = AppUpdateManagerFactory.create(LocalContext.current)
        // Returns an intent object that you use to check for an update.
        // Возвращает объект намерения, который вы используете для проверки наличия обновления.
        appUpdateInfoTask = appUpdateManager.appUpdateInfo
        // Checks that the platform will allow the specified type of update.
        // Проверяет, разрешит ли платформа указанный тип обновления.

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                // This example applies an immediate update. To apply a flexible update
                // instead, pass in AppUpdateType.FLEXIBLE
                // Этот пример применяет немедленное обновление. Чтобы применить гибкое обновление
                // вместо этого передайте AppUpdateType.FLEXIBLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                // Request the update.
                // Запросить обновление.
                UPDATEAVAILABLE = true
            } else UPDATEAVAILABLE = false
            availVersionCode = appUpdateInfo.availableVersionCode()
            Log.d("InAppUpdateManager", "availVersionCode=$availVersionCode")
            //appUpdateInfo.bytesDownloaded()
            //appUpdateInfo.totalBytesToDownload()
            packName = appUpdateInfo.packageName()
            Log.d("InAppUpdateManager", "package=$packName")
            //packName = appUpdateInfo.getFailedUpdatePreconditions()
        }
        if (BuildConfig.DEBUG) {
            Log.d("InAppUpdateManager", "UPDATE_AVAILABLE=$UPDATEAVAILABLE")
            Log.d("InAppUpdateManager", "package=$packName")
            Log.d("InAppUpdateManager", "availVersionCode=$availVersionCode")
        }

    }


    private fun checkForUpdates() {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
 //               && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
 //               requestUpdate(appUpdateInfo)
                UPDATEAVAILABLE = true
            }
            else UPDATEAVAILABLE=false
        }.addOnFailureListener {
            // Handle failure here
        }
    }

    private fun requestUpdate(appUpdateInfo: AppUpdateInfo) {
//        val updateOptions = AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()
        val updateOptions = AppUpdateOptions.newBuilder(AppUpdateType.FLEXIBLE).build()
        appUpdateManager.startUpdateFlowForResult(
            appUpdateInfo,
            activityResultLauncher,
            updateOptions
        )
    }

    fun registerListener(listener: InstallStateUpdatedListener) {
        appUpdateManager.registerListener(listener)
    }

    fun unregisterListener(listener: InstallStateUpdatedListener) {
        appUpdateManager.unregisterListener(listener)
    }
}
