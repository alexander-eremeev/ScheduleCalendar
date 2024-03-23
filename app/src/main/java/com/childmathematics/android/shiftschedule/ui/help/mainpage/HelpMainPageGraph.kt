package com.childmathematics.android.shiftschedule.ui.help.mainpage

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut


const val HELP_MAINPAGE_GRAPH_ROUTE = "help_MainPage_graph"
private const val HELP_MAINPAGE_DEEPLINK ="$ROOT_DEEPLINK/helpMainPage.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */
fun NavController.navigateToHelpMainPageGraph() {
    navigate(HELP_MAINPAGE_GRAPH_ROUTE)
}
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */
fun NavGraphBuilder.helpMainPageGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    navigation(startDestination = HELP_MAINPAGE_ROUTE,
        route = HELP_MAINPAGE_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = HELP_MAINPAGE_DEEPLINK }
        ),

        enterTransition = { screenFadeIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenFadeOut() },

        ){
        helpMainPageScreen(
            navController,
            modifier,
        )
    }
}
