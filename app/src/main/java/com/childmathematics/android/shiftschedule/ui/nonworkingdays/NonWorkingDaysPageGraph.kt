package com.childmathematics.android.shiftschedule.ui.nonworkingdays

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut

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
//    nonworkingDays01SummingPageGraph(navController,nonworkingDaysViewModel = nonworkingDaysViewModel)

    // =====================================

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
            /*
            nonworkingDays01PageUiState.state = rememberSelectableCalendarState(
              initialSelectionMode = SelectionMode.Period,
          )

             */
            //            val nonworkingDays01PageViewModel : Schedule01PageViewModel = viewModel()
            // =====================================

            // =====================================
            nonWorkingDaysPageScreen(
                navController,
                modifier,
                onOpenDrawer = onOpenDrawer, openDrawer = openDrawer,
               nonWorkingDaysViewModel = nonWorkingDaysViewModel
            )
        }
    }
}
