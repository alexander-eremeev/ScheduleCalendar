package com.childmathematics.android.shiftschedule.ui.main

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut
import com.childmathematics.android.shiftschedule.ui.in_app_update.UpdateViewModel
import kotlinx.coroutines.launch

const val MAIN_GRAPH_ROUTE = "main_graph"
private const val MAIN_DEEPLINK ="$ROOT_DEEPLINK/main.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */
fun NavController.navigateToMainPageGraph() {
    navigate(MAIN_GRAPH_ROUTE)
}
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */
fun NavGraphBuilder.mainPageGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    openDrawer: ()-> Unit,
    onOpenDrawer: Boolean,
    updateViewModel: UpdateViewModel
) {

    navigation(startDestination = MAIN_PAGE_ROUTE,
        route = MAIN_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = MAIN_DEEPLINK }
        ),

    enterTransition = { screenFadeIn() },
    exitTransition = { screenFadeOut() },
    popEnterTransition = { screenFadeIn() },
    popExitTransition = { screenFadeOut() },

    ){

        mainPageScreen(
            navController,
            modifier,
            onOpenDrawer,
            openDrawer,
            updateViewModel
            /*
            navigateToHelpMainPage = { navController.navigateToHelpMainPageGraph() },
            navigateToHelpGraphicsPage = { navController.navigateToHelpGraphicsGraph() },
            navigateToHelpAboutPage = { navController.navigateToHelpAboutGraph()  }

             */
        )
    }
}
