package com.childmathematics.android.shiftschedule

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut
import com.childmathematics.android.basement.lib.navigation.ui.screenSlideIn
import com.childmathematics.android.basement.lib.navigation.ui.screenSlideOut
import com.childmathematics.android.shiftschedule.ui.about.ABOUT_GRAPH_ROUTE
import com.childmathematics.android.shiftschedule.ui.about.aboutGraph

@Composable
internal fun RootHost(widthSizeClass: WindowWidthSizeClass,) {
    val rootController = rememberNavController()
    val navigationBarController = rememberNavController()


    NavHost(
        navController = rootController,

//        startDestination = SCHEDULE500_GRAPH_ROUTE,
//        startDestination = SCHEDULE01_GRAPH_ROUTE,

//        startDestination = MAIN_GRAPH_ROUTE,
        startDestination = ABOUT_GRAPH_ROUTE,
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
 //       mainPageGraph(navController = rootController)
        // =====================================
//        schedule500PageGraph(navController = rootController)
        // =====================================
//        schedule01PageGraph(navController = rootController)
        // =====================================
        aboutGraph(navController = rootController,openDrawer ={},onOpenDrawer = false)
        // =====================================
//        helpGraph(navController  = rootController)
//        localPolicesGraph(navController  = rootController)
//        licencesGraph(navController  = rootController)
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