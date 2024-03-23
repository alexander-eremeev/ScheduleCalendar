package com.childmathematics.android.shiftschedule.ui.localpolices

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut


const val LOCALPOLICES_GRAPH_ROUTE = "LocalPolices_graph"
private const val LOCALPOLICES_DEEPLINK ="$ROOT_DEEPLINK/privacy_police_app.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */
fun NavController.navigateToLocalPolicesGraph() {
    navigate(LOCALPOLICES_GRAPH_ROUTE)
}
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */
fun NavGraphBuilder.localPolicesGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    navigation(startDestination = LOCALPOLICES_ROUTE,
        route = LOCALPOLICES_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = LOCALPOLICES_DEEPLINK }
        ),

        enterTransition = { screenFadeIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenFadeOut() },

        ){
        localPolicesScreen(
            navController,
            modifier,
        )
    }
}
