package com.childmathematics.android.basement.lib.in_app_update

import android.content.IntentSender
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.childmathematics.android.shiftschedule.MainActivity
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.common.IntentSenderForResultStarter
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import java.lang.ref.WeakReference

/**
 * @Author: SOHAIB AHMED
 * @Date: 26,December,2022
 * @Accounts
 *      -> https://github.com/epegasus
 *      -> https://stackoverflow.com/users/20440272/sohaib-ahmed
 */

class ScheduleAppUpdateManager(private val activity: MainActivity) {

    private val appUpdateManager by lazy { AppUpdateManagerFactory.create(activity) }
    private val weakReference = WeakReference(activity)

    private var updateType = AppUpdateType.IMMEDIATE

    private var appUpdateInfo: AppUpdateInfo? = null
    private var callback: ((isUpdated: Boolean, message: String) -> Unit)? = null
    lateinit var messageStr: String

    private val updateFlowResultLauncher = weakReference.get()?.registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        messageStr = ""
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            checkIfUpdateInstalled()
        } else {
            callback?.invoke(false, "Update Cancelled by User")
            Log.d("ScheduleAppUpdateManager",
                "updateFlowResultLauncher: resultCode="+result.resultCode+" Update Cancelled by User")
            messageStr = "ScheduleAppUpdateManager"+ "updateFlowResultLauncher: resultCode="+result.resultCode+
                    " Update Cancelled by User"
        }
    }

    /**
     *    Types of AppUpdate:
     *     -> AppUpdateType.IMMEDIATE (1)
     *     -> AppUpdateType.FLEXIBLE (0)
     */

    fun setUpdateType(updateType: Int = AppUpdateType.IMMEDIATE) {
        this.updateType = updateType
    }

    /**
     *    Types of UpdateAvailability:
     *     -> UpdateAvailability.UNKNOWN
     *     -> UpdateAvailability.UPDATE_NOT_AVAILABLE
     *     -> UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
     *     -> UpdateAvailability.UPDATE_AVAILABLE
     */
@Composable
    fun checkForUpdateApp(callback: (isAvailable: Boolean, message: String) -> Unit) {
        messageStr = ""

        appUpdateManager.appUpdateInfo.addOnCompleteListener {
            if (it.isSuccessful) {
                appUpdateInfo = it.result
                when (appUpdateInfo?.updateAvailability()) {
                    UpdateAvailability.UNKNOWN -> {callback.invoke(false, "Unknown Response")
//;                        UpdateAvailability.UNKNOWN -> {callback.invoke(false, "Unknown Response")
                        Log.d("ScheduleAppUpdateManager", "checkForUpdate: isAvailable=false"+" Unknown Response ")
                        messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=false"+" Unknown Response"
                    }
//                    UpdateAvailability.UPDATE_NOT_AVAILABLE -> callback.invoke(false, "No Updates Available")
                    UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                        callback.invoke(false, "No Updates Available")
                        Log.d("ScheduleAppUpdateManager", "checkForUpdate: isAvailable=false"+" No Updates Available")
                        messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=false"+
                                " No Updates Available"
                    }
//                    UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> callback.invoke(false,
//                    "Update is in Progress. Please Wait")
                    UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                        callback.invoke(false, "Update is in Progress. Please Wait")
                        Log.d("ScheduleAppUpdateManager", "checkForUpdate: isAvailable=false"+
                                " Update is in Progress. Please Wait")
                        messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=false"+
                                " Update is in Progress. Please Wait"

                    }
                    UpdateAvailability.UPDATE_AVAILABLE -> {
                        if (appUpdateInfo?.isUpdateTypeAllowed(updateType) == true) {
                            callback.invoke(true, "Update Available")
                            Log.d("ScheduleAppUpdateManager", "checkForUpdate: isAvailable=true"+" Update Available")
                            messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=true"+
                                    " Update Available"
                        }
                        else {
                            callback.invoke(false, "This type of update is not available.")
                            Log.d("ScheduleAppUpdateManager", "checkForUpdate: isAvailable=false"+
                                    " This type of update is not available.")
                            messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=false"+
                                    " This type of update is not available."
                         }
                    }
                }
            } else {
                callback.invoke(false, it.exception?.message.toString())
                Log.d("ScheduleAppUpdateManager", "checkForUpdate: error "+it.exception?.message.toString())
                messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: error="+it.exception?.message.toString()
            }
        }
    }
//--------------------------------------------------------------

    fun checkForUpdate(callback: (isAvailable: Boolean, message: String) -> Unit) {
        messageStr = ""

        appUpdateManager.appUpdateInfo.addOnCompleteListener {
            if (it.isSuccessful) {
                appUpdateInfo = it.result
                when (appUpdateInfo?.updateAvailability()) {
                    UpdateAvailability.UNKNOWN -> {callback.invoke(false, "Unknown Response")
//;                        UpdateAvailability.UNKNOWN -> {callback.invoke(false, "Unknown Response")
                        Log.d("ScheduleAppUpdateManager", "checkForUpdate: isAvailable=false"+" Unknown Response ")
                        messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=false"+" Unknown Response"
                    }
//                    UpdateAvailability.UPDATE_NOT_AVAILABLE -> callback.invoke(false, "No Updates Available")
                    UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                        callback.invoke(false, "No Updates Available")
                        Log.d("ScheduleAppUpdateManager", "checkForUpdate: isAvailable=false"+" No Updates Available")
                        messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=false"+
                                " No Updates Available"
                    }
//                    UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> callback.invoke(false,
//                    "Update is in Progress. Please Wait")
                    UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                        callback.invoke(false, "Update is in Progress. Please Wait")
                        Log.d("ScheduleAppUpdateManager", "checkForUpdate: isAvailable=false"+
                                " Update is in Progress. Please Wait")
                        messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=false"+
                                " Update is in Progress. Please Wait"

                    }
                    UpdateAvailability.UPDATE_AVAILABLE -> {
                        if (appUpdateInfo?.isUpdateTypeAllowed(updateType) == true) {
                            callback.invoke(true, "Update Available")
                            Log.d("ScheduleAppUpdateManager", "checkForUpdate: isAvailable=true"+" Update Available")
                            messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=true"+
                                    " Update Available"
                        }
                        else {
                            callback.invoke(false, "This type of update is not available.")
                            Log.d("ScheduleAppUpdateManager", "checkForUpdate: isAvailable=false"+
                                    " This type of update is not available.")
                            messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: isAvailable=false"+
                                    " This type of update is not available."
                        }
                    }
                }
            } else {
                callback.invoke(false, it.exception?.message.toString())
                Log.d("ScheduleAppUpdateManager", "checkForUpdate: error "+it.exception?.message.toString())
                messageStr = "ScheduleAppUpdateManager"+ "checkForUpdate: error="+it.exception?.message.toString()
            }
        }
    }

//==========================================================
    /**
     * @see : MY_REQUEST_CODE -> Ignore this Constant
     */

    fun requestForUpdate(callback: (isUpdated: Boolean, message: String) -> Unit) {
        this.callback = callback

        val starter = IntentSenderForResultStarter { intent, _, fillInIntent, flagsMask, flagsValues, _, _ ->
            val request = IntentSenderRequest.Builder(intent).setFillInIntent(fillInIntent).setFlags(flagsValues, flagsMask).build()
            updateFlowResultLauncher?.launch(request)
        }
        try {
            appUpdateInfo?.let { info ->
                appUpdateManager.startUpdateFlowForResult(info, updateType, starter, 100)
            } ?: kotlin.run {
                callback.invoke(false, "Failed to Launch Update Flow, try again later")
                Log.d("ScheduleAppUpdateManager", "requestForUpdate: "+"Failed to Launch Update Flow, try again later")
            }
        } catch (ex: IntentSender.SendIntentException) {
            callback.invoke(false, ex.message.toString())
            Log.d("ScheduleAppUpdateManager", "requestForUpdate: error"+ex.message.toString())
        }
    }

    /**
     *  If the update is downloaded but not installed, notify the user to complete the update.
     */

    fun checkIfUpdateInstalled() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            when (appUpdateInfo.installStatus()) {
                InstallStatus.DOWNLOADED -> popupSnackbarForCompleteUpdate()
                InstallStatus.INSTALLED -> {
                    callback?.invoke(true, "Updated Successfully")
                    Log.d("ScheduleAppUpdateManager", "checkIfUpdateInstalled: Updated Successfully")
                }
                InstallStatus.CANCELED -> {callback?.invoke(false, "Cancelled by User")
                    Log.d("ScheduleAppUpdateManager", "checkIfUpdateInstalled: Cancelled by User")
                    }
                else  ->{
                    callback?.invoke(false, "Failed to Update")
                    Log.d("ScheduleAppUpdateManager", "checkIfUpdateInstalled: Failed to Update")
                }
            }
        }
    }

    // Displays the snackbar notification and call to action.
    private fun popupSnackbarForCompleteUpdate() {
    }
}