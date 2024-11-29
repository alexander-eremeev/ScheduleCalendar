package com.childmathematics.android.shiftschedule.ui.nonworkingdays

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

internal const val NONWORKINGDAYS_PAGE_ROUTE = "nonworkingdaysPage"
internal fun NavGraphBuilder.nonWorkingDaysPageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    onOpenDrawer: Boolean,
    nonWorkingDaysViewModel : NonWorkingDaysViewModel,
    // =====================================
//    state: CalendarState<DynamicSelectionState>,
    /*
    navigateToHelpNonworkingDaysPage: () -> Unit,
    navigateToHelpGraphicsPage: () -> Unit,
    navigateToHelpAboutPage: () -> Unit

     */
) {
    composable(route = NONWORKINGDAYS_PAGE_ROUTE) {

        NonWorkingDaysPageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            onOpenDrawer = onOpenDrawer,openDrawer =openDrawer,
//            navigateToNonworkingDaysSummingPage = { navController.navigateToNonworkingDaysSummingPageGraph()},
            nonWorkingDaysViewModel,
        )
    }
}