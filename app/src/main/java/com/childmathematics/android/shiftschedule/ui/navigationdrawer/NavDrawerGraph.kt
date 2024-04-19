package com.childmathematics.android.shiftschedule.ui.navigationdrawer

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut
import com.childmathematics.android.shiftschedule.ui.about.aboutGraph
import com.childmathematics.android.shiftschedule.ui.about.navigateToAboutGraph
import com.childmathematics.android.shiftschedule.ui.help.aboutpage.navigateToHelpAboutGraph
import com.childmathematics.android.shiftschedule.ui.help.graphicspage.navigateToHelpGraphicsGraph
import com.childmathematics.android.shiftschedule.ui.help.helpGraph
import com.childmathematics.android.shiftschedule.ui.help.mainpage.navigateToHelpMainPageGraph
import com.childmathematics.android.shiftschedule.ui.licences.licencesGraph
import com.childmathematics.android.shiftschedule.ui.localpolices.localPolicesGraph
import com.childmathematics.android.shiftschedule.ui.main.MAIN_GRAPH_ROUTE
import com.childmathematics.android.shiftschedule.ui.main.mainPageGraph
import com.childmathematics.android.shiftschedule.ui.main.navigateToMainPageGraph
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.schedule01PageGraph
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.schedule500PageGraph


const val NAVDRAWER_GRAPH_ROUTE = "navDrawer_graph"
private const val NAVDRAWER_DEEPLINK ="$ROOT_DEEPLINK/privacy_police_app.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */
fun NavController.navigateToNavDrawerGraph() {
    navigate(NAVDRAWER_GRAPH_ROUTE)
}
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */
fun NavGraphBuilder.navDrawerGraph(
    widthSizeClass: WindowWidthSizeClass,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    ) {
    // =====================================
    mainPageGraph(navController = navController,openDrawer ={})
    // =====================================
    schedule500PageGraph(navController = navController)
    // =====================================
    schedule01PageGraph(navController = navController)
    // =====================================
    aboutGraph(navController = navController,openDrawer ={})
    // =====================================
//    helpGraph(navController  = navController)
//    localPolicesGraph(navController  = navController)
//    licencesGraph(navController  = navController)
    // =====================================

    navigation(
        startDestination = NAVDRAWER_ROUTE,
        route = NAVDRAWER_GRAPH_ROUTE,

        enterTransition = { screenFadeIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenFadeOut() },

        ){

        navDrawerScreen(
            widthSizeClass,
            navController,
            modifier,
            /*
            navigateToMainPage = { navController.navigateToMainPageGraph() },
            navigateToSchedule01Page = { navController.navigateToSchedule01PageGraph() },
            navigateToSchedule500Page = { navController.navigateToSchedule500PageGraph() },
            navigateToAboutPage = { navController.navigateToAboutGraph()  }

             */

        )
    }
}
