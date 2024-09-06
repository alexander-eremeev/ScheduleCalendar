package com.childmathematics.android.shiftschedule

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.childmathematics.android.basement.lib.in_app_update.ScheduleAppUpdateManager
import com.childmathematics.android.shiftschedule.ui.navigation.drawer.DrawerApp
import com.google.android.play.core.install.model.AppUpdateType

class MainActivity : ComponentActivity() {
    private lateinit var activityResultLauncher: ActivityResultLauncher<IntentSenderRequest>

//    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val scheduleAppUpdateManager = ScheduleAppUpdateManager(this)


    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        // -----------------------------------------------
        // сделать так, чтобы ваше приложение отображалось от края до края, используя всю ширину
        // и высоту экрана, рисуя за системными панелями.
        enableEdgeToEdge()
        // ==============================================
        super.onCreate(savedInstanceState)
        // ------------------------------------------------------------------
        // an activity result launcher registered via registerForActivityResult
        // средство запуска результата активности, зарегистрированное через registerForActivityResult
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
                result ->
            // Handle the result of the update flow here
        }
//-------------------------------------------------------------------------
//        val appContainer = (application as MainApplication).container
        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
//            checkForUpdate()
            DrawerApp( widthSizeClass)
//            RootHost(widthSizeClass) //
        }
    }
    //-----------------------------------------------
    /**
     *    Types of AppUpdate:
     *     -> AppUpdateType.IMMEDIATE
     *     -> AppUpdateType.FLEXIBLE
     */

    private fun checkForUpdate() {
        scheduleAppUpdateManager.setUpdateType(AppUpdateType.FLEXIBLE)
        scheduleAppUpdateManager.checkForUpdate { isAvailable, _ ->
//        scheduleAppUpdateManager.checkForUpdate { isAvailable, message ->
//            binding.tvText.text = message
            if (isAvailable) {
            //    requestForUpdate()
            }
        }
    }

    private fun requestForUpdate() {
        scheduleAppUpdateManager.requestForUpdate { isUpdated, _ ->
 //           binding.tvText.text = message
            if (isUpdated) {
                // Proceed with Code...
                Log.d("TAG", "requestForUpdate: Running App...")
            }
        }
    }

    // Checks that the update is not stalled during 'onResume()'
    override fun onResume() {
        super.onResume()
        scheduleAppUpdateManager.checkIfUpdateInstalled()
    }

    private fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    //===============================================

}
