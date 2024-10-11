package com.childmathematics.android.shiftschedule.ui.inappupdate

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut


const val IN_APP_UPDATEPAGE_GRAPH_ROUTE = "in_App_UpdatePage_graph"
private const val IN_APP_UPDATEPAGE_DEEPLINK ="$ROOT_DEEPLINK/in_App_UpdatePage.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */
fun NavController.navigateToInAppUpdatePageGraph() {
    navigate(IN_APP_UPDATEPAGE_GRAPH_ROUTE)
}
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */
fun NavGraphBuilder.inAppUpdatePageGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    updateViewModel: UpdateViewModel

) {
    navigation(startDestination = IN_APP_UPDATEPAGE_ROUTE,
        route = IN_APP_UPDATEPAGE_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = IN_APP_UPDATEPAGE_DEEPLINK }
        ),

        enterTransition = { screenFadeIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenFadeOut() },

        ){
        inAppUpdatePageScreen(
            navController,
            modifier,
            updateViewModel
        )
    }
}
