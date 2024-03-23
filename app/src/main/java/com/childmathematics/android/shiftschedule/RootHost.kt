package com.childmathematics.android.shiftschedule

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut
import com.childmathematics.android.basement.lib.navigation.ui.screenSlideIn
import com.childmathematics.android.basement.lib.navigation.ui.screenSlideOut
import com.childmathematics.android.shiftschedule.ui.about.aboutGraph
import com.childmathematics.android.shiftschedule.ui.help.helpGraph
import com.childmathematics.android.shiftschedule.ui.licences.licencesGraph
import com.childmathematics.android.shiftschedule.ui.localpolices.localPolicesGraph
import com.childmathematics.android.shiftschedule.ui.main.MAIN_GRAPH_ROUTE
import com.childmathematics.android.shiftschedule.ui.main.mainPageGraph

@Composable
internal fun RootHost() {
    val rootController = rememberNavController()
    val navigationBarController = rememberNavController()

    NavHost(
        navController = rootController,
        startDestination = MAIN_GRAPH_ROUTE,
//        startDestination = ABOUT_GRAPH_ROUTE,
//        startDestination = HELP_GRAPH_ROUTE,
        /*
        // =====================================
        AnimatedContentTransitionScope предоставляет функции, которые удобны и применимы только
        в контексте AnimatedContent
         */
        enterTransition = { screenSlideIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenSlideOut() },
    ) {
        // =====================================
        mainPageGraph(navController = rootController)
        // =====================================
        aboutGraph(navController = rootController)
        // =====================================
        helpGraph(navController  = rootController)
        localPolicesGraph(navController  = rootController)
        licencesGraph(navController  = rootController)
        // =====================================

//        helpGraph(navController = rootController, onBackClick = {rootController.popBackStack()})
        // =====================================

        /*
        navigationBarHost(
            navController = navigationBarController,
            // ------------------------------------------
            onLoginClick = {
                rootController.navigateToLoginGraph()
            },
            onJoinClubClick = {
                rootController.navigateToRegistrationGraph()
            },
            onGoToOrderClick = {
                rootController.navigateToOrderGraph()
            }
            // =====================================
        )
        loginGraph(
            navController = rootController,
            onRegisterClick = {
                rootController.navigateToRegistrationGraph()
            }
        )
        // =====================================
        registrationGraph(navController = rootController)
        // =====================================
        orderGraph(
            navController = rootController,
            onProductClick = { productId ->
                rootController.popBackStack(ORDER_GRAPH_ROUTE, inclusive = true)
                navigationBarController.navigateToProductCardGraph(productId)
            }
        )

         */
        // =====================================
    }
}