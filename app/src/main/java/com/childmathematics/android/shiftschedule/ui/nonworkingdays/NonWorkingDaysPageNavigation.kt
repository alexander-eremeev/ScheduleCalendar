package com.childmathematics.android.shiftschedule.ui.nonworkingdays

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.shiftschedule.ui.nonworkingdays.year.navigateToNonWorkingDaysYearPageGraph

internal const val NONWORKINGDAYS_PAGE_ROUTE = "nonworkingdaysPage"
internal fun NavGraphBuilder.nonWorkingDaysPageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    onOpenDrawer: Boolean,
    nonWorkingDaysViewModel : NonWorkingDaysViewModel,
) {
    composable(route = NONWORKINGDAYS_PAGE_ROUTE) {

        NonWorkingDaysPageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            onOpenDrawer = onOpenDrawer,openDrawer =openDrawer,
            navigateToNonWorkingDaysYearPage = { navController.navigateToNonWorkingDaysYearPageGraph()},
            nonWorkingDaysViewModel,
        )
    }
}