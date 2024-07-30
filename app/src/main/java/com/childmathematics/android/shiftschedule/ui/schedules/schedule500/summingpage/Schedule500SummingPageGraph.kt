package com.childmathematics.android.shiftschedule.ui.schedules.schedule500.summingpage

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut
import com.childmathematics.android.shiftschedule.ui.ScheduleViewModel


const val SCHEDULE500_SUMMINGPAGE_GRAPH_ROUTE = "schedule500_SummingPage_graph"
private const val SCHEDULE500_SUMMINGPAGE_DEEPLINK ="$ROOT_DEEPLINK/schedule01_SummingPage.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */

fun NavController.navigateToSchedule500SummingPageGraph() {
    navigate(SCHEDULE500_SUMMINGPAGE_GRAPH_ROUTE)
}
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */

fun NavGraphBuilder.schedule500SummingPageGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
//    state: CalendarState<DynamicSelectionState>
//    schedule01PageViewModel:,
    scheduleViewModel: ScheduleViewModel,

    ) {
    navigation(startDestination = SCHEDULE500_SUMMINGPAGE_ROUTE,
        route = SCHEDULE500_SUMMINGPAGE_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = SCHEDULE500_SUMMINGPAGE_DEEPLINK }
        ),

        enterTransition = { screenFadeIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenFadeOut() },

        ){
        schedule500SummingPageScreen(
            navController,
            modifier,
            scheduleViewModel = scheduleViewModel
        )
    }
}
