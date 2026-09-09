package com.childmathematics.android.shiftschedule.ui.help.graphicspage

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut
import com.childmathematics.android.shiftschedule.ui.help.Graphics.HELP_GRAPHICS_ROUTE
import com.childmathematics.android.shiftschedule.ui.help.Graphics.helpGraphicsScreen


const val HELP_GRAPHICS_GRAPH_ROUTE = "help_Graphics_graph"
private const val HELP_GRAPHICS_DEEPLINK ="$ROOT_DEEPLINK/help.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */
fun NavController.navigateToHelpGraphicsGraph() {
    navigate(HELP_GRAPHICS_GRAPH_ROUTE)
}
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */
fun NavGraphBuilder.helpGraphicsGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    navigation(startDestination = HELP_GRAPHICS_ROUTE,
        route = HELP_GRAPHICS_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = HELP_GRAPHICS_DEEPLINK }
        ),

        enterTransition = { screenFadeIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenFadeOut() },

        ){
        helpGraphicsScreen(
            navController,
            modifier,
        )
    }
}
