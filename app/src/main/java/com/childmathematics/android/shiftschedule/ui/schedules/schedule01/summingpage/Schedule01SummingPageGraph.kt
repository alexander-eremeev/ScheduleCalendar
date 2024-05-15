package com.childmathematics.android.shiftschedule.ui.schedules.schedule01.summingpage

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.composecalendar.CalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut


const val SCHEDULE01_SUMMINGPAGE_GRAPH_ROUTE = "schedule01_SummingPage_graph"
private const val SCHEDULE01_SUMMINGPAGE_DEEPLINK ="$ROOT_DEEPLINK/schedule01_SummingPage.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */
fun NavController.navigateToSchedule01SummingPageGraph() {
    navigate(SCHEDULE01_SUMMINGPAGE_GRAPH_ROUTE)
}
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */
fun NavGraphBuilder.schedule01SummingPageGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
//    state: CalendarState<DynamicSelectionState>
) {
    navigation(startDestination = SCHEDULE01_SUMMINGPAGE_ROUTE,
        route = SCHEDULE01_SUMMINGPAGE_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = SCHEDULE01_SUMMINGPAGE_DEEPLINK }
        ),

        enterTransition = { screenFadeIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenFadeOut() },

        ){
        schedule01SummingPageScreen(
            navController,
            modifier,
//            state
        )
    }
}
