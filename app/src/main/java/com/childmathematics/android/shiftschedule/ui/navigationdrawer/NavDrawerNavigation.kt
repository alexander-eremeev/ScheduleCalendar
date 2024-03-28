package com.childmathematics.android.shiftschedule.ui.navigationdrawer

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.shiftschedule.ui.about.navigateToAboutGraph
import com.childmathematics.android.shiftschedule.ui.main.navigateToMainPageGraph
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.navigateToSchedule01PageGraph
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.navigateToSchedule500PageGraph

internal const val NAVDRAWER_ROUTE = "navDrawer"
internal fun NavGraphBuilder.navDrawerScreen(
    widthSizeClass: WindowWidthSizeClass,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    composable(route = NAVDRAWER_ROUTE) {
        NavDrawerScreen(
            widthSizeClass,
            modifier,
            onBackClick = { navController.popBackStack()},
            navigateToMainPage = { navController.navigateToMainPageGraph() },
            navigateToSchedule01Page = { navController.navigateToSchedule01PageGraph() },
            navigateToSchedule500Page = { navController.navigateToSchedule500PageGraph() },
            navigateToAboutPage = { navController.navigateToAboutGraph()  }

        )
    }
}