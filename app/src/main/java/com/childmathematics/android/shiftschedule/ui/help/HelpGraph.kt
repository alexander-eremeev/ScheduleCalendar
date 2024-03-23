package com.childmathematics.android.shiftschedule.ui.help

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut
import com.childmathematics.android.shiftschedule.ui.help.aboutpage.helpAboutGraph
import com.childmathematics.android.shiftschedule.ui.help.aboutpage.navigateToHelpAboutGraph
import com.childmathematics.android.shiftschedule.ui.help.graphicspage.helpGraphicsGraph
import com.childmathematics.android.shiftschedule.ui.help.graphicspage.navigateToHelpGraphicsGraph
import com.childmathematics.android.shiftschedule.ui.help.mainpage.helpMainPageGraph
import com.childmathematics.android.shiftschedule.ui.help.mainpage.navigateToHelpMainPageGraph

const val HELP_GRAPH_ROUTE = "help_graph"
private const val HELP_DEEPLINK ="$ROOT_DEEPLINK/help.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */
fun NavController.navigateToHelpGraph() {
    navigate(HELP_GRAPH_ROUTE)
}
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */
fun NavGraphBuilder.helpGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    // =====================================
    helpMainPageGraph(navController)
    helpGraphicsGraph(navController)
    helpAboutGraph(navController)
    // =====================================

    navigation(startDestination = HELP_PAGE_ROUTE,
        route = HELP_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = HELP_DEEPLINK }
        ),

    enterTransition = { screenFadeIn() },
    exitTransition = { screenFadeOut() },
    popEnterTransition = { screenFadeIn() },
    popExitTransition = { screenFadeOut() },

    ){
        helpPageScreen(
            navController,
            modifier,
            navigateToHelpMainPage = { navController.navigateToHelpMainPageGraph() },
            navigateToHelpGraphicsPage = { navController.navigateToHelpGraphicsGraph() },
            navigateToHelpAboutPage = { navController.navigateToHelpAboutGraph()  }
        )
    }
}
