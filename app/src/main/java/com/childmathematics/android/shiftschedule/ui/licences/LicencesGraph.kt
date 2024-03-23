package com.childmathematics.android.shiftschedule.ui.licences

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut


const val LICENCES_GRAPH_ROUTE = "licences_graph"
private const val LICENCES_DEEPLINK ="$ROOT_DEEPLINK/open_source_licenses.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */
fun NavController.navigateToLicencesGraph() {
    navigate(LICENCES_GRAPH_ROUTE)
}
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */
fun NavGraphBuilder.licencesGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    navigation(startDestination = LICENCES_ROUTE,
        route = LICENCES_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = LICENCES_DEEPLINK }
        ),

        enterTransition = { screenFadeIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenFadeOut() },

        ){
        licencesScreen(
            navController,
            modifier,
        )
    }
}
