package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.year

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.NonWorkingDaysViewModel


const val NONWORKINGDAYS_YEARPAGE_GRAPH_ROUTE = "nonWorkingDaysYearPage_graph"
private const val NONWORKINGDAYS_YEARPAGE_DEEPLINK ="$ROOT_DEEPLINK/nonWorkingDaysYearPage.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */

fun NavController.navigateToNonWorkingDaysYearPageGraph() {
    navigate(NONWORKINGDAYS_YEARPAGE_GRAPH_ROUTE)
}
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */

fun NavGraphBuilder.nonWorkingDaysYearPageGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
//    state: CalendarState<DynamicSelectionState>
//    schedule01PageViewModel:,
    nonWorkingDaysViewModel: NonWorkingDaysViewModel,

    ) {
    navigation(startDestination = NONWORKINGDAYS_YEARPAGE_ROUTE,
        route = NONWORKINGDAYS_YEARPAGE_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = NONWORKINGDAYS_YEARPAGE_DEEPLINK }
        ),

        enterTransition = { screenFadeIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenFadeOut() },

        ){
        nonWorkingDaysYearPageScreen(
            navController,
            modifier,
            nonWorkingDaysViewModel = nonWorkingDaysViewModel
        )
    }
}
