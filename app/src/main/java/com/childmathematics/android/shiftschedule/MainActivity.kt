package com.childmathematics.android.shiftschedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContent {
            MainScreen()

        }
    }
    @Composable
    fun MainScreen() {
        StaticCalendar()
    }
}

 */
import com.childmathematics.android.shiftschedule.navigation.NavigateScreen1
import com.childmathematics.android.shiftschedule.shiftads.AdBannerNetworkApp
import com.childmathematics.android.shiftschedule.shifttodo.todo.TodoScreen
import com.childmathematics.android.shiftschedule.shifttodo.todo.TodoViewModel

import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    val todoViewModel by viewModels<TodoViewModel>()

//    @ExperimentalCoroutinesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MainScreen(todoViewModel)
//            AdBannerNetworkApp()
            NavigateScreen1(todoViewModel)
        }
        // initialize the Mobile Ads SDK
        MobileAds.initialize(this) { }

        // load the interstitial ad
        loadInterstitial(this)

        // add the interstitial ad callbacks
        addInterstitialCallbacks(this)

    }
}
/*
@Composable
fun MainScreen(todoViewModel: TodoViewModel) {

    MaterialTheme(
        colors = if (isSystemInDarkTheme()) darkColors() else lightColors()
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "main") {
                composable("main") { MainMenu(navController = navController) }
                composable("static") { StaticCalendarSample() }
                composable("navigation") { Tutorial2_9Screen1() }
              composable("mobads") { AdNetworkApp()
                         }
                composable("todo") { TodoActivityScreen(todoViewModel) }
                composable("viewmodel") { ViewModelSample() }
            }
        }
    }
}

@Composable
fun MainMenu(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate("static") }) {
            Text(text = "1  Static Calendar")
        }
        Button(onClick = { navController.navigate("navigation") }) {
            Text(text = "5 Навигация ")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("mobads") }) {
            Text(text = "4 mobile Ads")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("todo") }) {
            Text(text = "3  ToDO")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("viewmodel") }) {
            Text(text = "2  ViewModel")
        }
    }
}



*/
