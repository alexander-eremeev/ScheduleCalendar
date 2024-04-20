package com.childmathematics.android.shiftschedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.childmathematics.android.shiftschedule.ui.navigation.drawer.DrawerNewApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        // -----------------------------------------------
        // сделать так, чтобы ваше приложение отображалось от края до края, используя всю ширину
        // и высоту экрана, рисуя за системными панелями.
        enableEdgeToEdge()
        // ==============================================
        super.onCreate(savedInstanceState)
        // ------------------------------------------------------------------
//        val appContainer = (application as MainApplication).container
        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
// ???           JetnewsApp(appContainer, widthSizeClass)
            DrawerNewApp( widthSizeClass)
//            RootHost(widthSizeClass)
        }
    }
}
