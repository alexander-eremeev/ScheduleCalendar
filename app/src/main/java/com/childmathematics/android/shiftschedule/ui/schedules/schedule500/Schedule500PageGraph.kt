package com.childmathematics.android.shiftschedule.ui.schedules.schedule500

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut

const val SCHEDULE500_GRAPH_ROUTE = "schedule500_graph"
private const val SCHEDULE500_DEEPLINK ="$ROOT_DEEPLINK/schedule500.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */
fun NavController.navigateToSchedule500PageGraph() {
    navigate(SCHEDULE500_GRAPH_ROUTE)
}
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */
fun NavGraphBuilder.schedule500PageGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
     navigation(startDestination = SCHEDULE500_PAGE_ROUTE,
        route = SCHEDULE500_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = SCHEDULE500_DEEPLINK }
        ),

    enterTransition = { screenFadeIn() },
    exitTransition = { screenFadeOut() },
    popEnterTransition = { screenFadeIn() },
    popExitTransition = { screenFadeOut() },

    ){
        schedule500PageScreen(
            navController,
            modifier,
            /*
            navigateToHelpSchedule500Page = { navController.navigateToHelpSchedule500PageGraph() },
            navigateToHelpGraphicsPage = { navController.navigateToHelpGraphicsGraph() },
            navigateToHelpAboutPage = { navController.navigateToHelpAboutGraph()  }

             */
        )
    }
}
