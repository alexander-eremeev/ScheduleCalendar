package com.childmathematics.android.shiftschedule

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.childmathematics.android.shiftschedule.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.ui.navigation.DrawerApp
import com.childmathematics.android.shiftschedule.ui.navigationdrawer.drawer.DrawerNewApp

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
