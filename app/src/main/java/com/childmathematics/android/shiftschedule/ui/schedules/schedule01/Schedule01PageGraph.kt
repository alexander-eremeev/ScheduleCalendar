package com.childmathematics.android.shiftschedule.ui.schedules.schedule01

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut

const val SCHEDULE01_GRAPH_ROUTE = "schedule01_graph"
private const val SCHEDULE01_DEEPLINK ="$ROOT_DEEPLINK/schedule01.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */
fun NavController.navigateToSchedule01PageGraph() {
    navigate(SCHEDULE01_GRAPH_ROUTE)
}
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */
fun NavGraphBuilder.schedule01PageGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
     navigation(startDestination = SCHEDULE01_PAGE_ROUTE,
        route = SCHEDULE01_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = SCHEDULE01_DEEPLINK }
        ),

    enterTransition = { screenFadeIn() },
    exitTransition = { screenFadeOut() },
    popEnterTransition = { screenFadeIn() },
    popExitTransition = { screenFadeOut() },

    ){
        schedule01PageScreen(
            navController,
            modifier,
            /*
            navigateToHelpSchedule01Page = { navController.navigateToHelpSchedule01PageGraph() },
            navigateToHelpGraphicsPage = { navController.navigateToHelpGraphicsGraph() },
            navigateToHelpAboutPage = { navController.navigateToHelpAboutGraph()  }

             */
        )
    }
}
