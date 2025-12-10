package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.childmathematics.android.basement.lib.composecalendar.CalendarState
import com.childmathematics.android.basement.lib.composecalendar.rememberSelectableCalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionMode
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.year.nonWorkingDaysYearPageGraph

const val NONWORKINGDAYS_GRAPH_ROUTE = "nonworkingdays_graph"
private const val NONWORKINGDAYS_DEEPLINK ="$ROOT_DEEPLINK/nonworkingdays.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */
fun NavController.navigateToNonworkingdaysPageGraph() {
    navigate(NONWORKINGDAYS_GRAPH_ROUTE)
}
//=============================
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */
fun NavGraphBuilder.nonWorkingDaysPageGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    onOpenDrawer: Boolean,
    nonWorkingDaysViewModel: NonWorkingDaysViewModel,
) {
    // =====================================
//        nonworkingDays01SummingPageGraph(navController)

    nonWorkingDaysYearPageGraph(navController,nonWorkingDaysViewModel = nonWorkingDaysViewModel)

    // =====================================
/*
// если нет вложенных меню то не работает
// протестировать правильность использования !!!!

    navigation(startDestination = NONWORKINGDAYS_PAGE_ROUTE,
        route = NONWORKINGDAYS_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = NONWORKINGDAYS_DEEPLINK }
        ),

        enterTransition = { screenFadeIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenFadeOut() },

        ){
        composable(route = NONWORKINGDAYS_PAGE_ROUTE) {
            nonWorkingDaysPageScreen(
                navController,
                modifier,
                onOpenDrawer = onOpenDrawer, openDrawer = openDrawer,
                nonWorkingDaysViewModel = nonWorkingDaysViewModel
            )
        }
    }
    */
}
