package com.childmathematics.android.basement.lib.in_app_update

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability

object InAppUpdateManager {

 //   private lateinit var appUpdateManager: AppUpdateManager
    lateinit var appUpdateManager: AppUpdateManager

    //Launcher для заранее подготовленного вызова для начала процесса выполнения ActivityResultContract,
    // который принимает I в качестве обязательных входных данных.
//    private lateinit var activityResultLauncher: ActivityResultLauncher<IntentSenderRequest>
    lateinit var activityResultLauncher: ActivityResultLauncher<IntentSenderRequest>
    var UPDATE_AVAILABLE:Boolean = false
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
        checkForUpdates()
    }
    fun initCheckForUpdates(context: Context, launcher: ActivityResultLauncher<IntentSenderRequest>) {
        appUpdateManager = AppUpdateManagerFactory.create(context)
        activityResultLauncher = launcher
        checkForUpdates()
        /*
                            val context = LocalContext.current
                    val appUpdateManager = AppUpdateManagerFactory.create(LocalContext.current)
                    // Returns an intent object that you use to check for an update.
                    // Возвращает объект намерения, который вы используете для проверки наличия обновления.
                    val appUpdateInfoTask = appUpdateManager.appUpdateInfo
                    // Checks that the platform will allow the specified type of update.
                    // Проверяет, разрешит ли платформа указанный тип обновления.

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
                            }
                        }

         */
    }


    private fun checkForUpdates() {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
 //               && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
 //               requestUpdate(appUpdateInfo)
                UPDATE_AVAILABLE = true
            }
            else UPDATE_AVAILABLE=false
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
