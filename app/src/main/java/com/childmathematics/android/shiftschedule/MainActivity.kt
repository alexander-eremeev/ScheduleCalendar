package com.childmathematics.android.shiftschedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.childmathematics.android.shiftschedule.ui.navigation.drawer.DrawerApp

class MainActivity : ComponentActivity() {
    private lateinit var activityResultLauncher: ActivityResultLauncher<IntentSenderRequest>

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
            DrawerApp( widthSizeClass)
//            RootHost(widthSizeClass) //
        }
    }
}
