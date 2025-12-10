package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.year

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.basement.lib.composecalendar.CalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.NonWorkingDaysViewModel


internal const val NONWORKINGDAYS_YEARPAGE_ROUTE = "nonWorkingDaysYearPage"
internal fun NavGraphBuilder.nonWorkingDaysYearPageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    nonWorkingDaysViewModel: NonWorkingDaysViewModel,

    ) {
    composable(route = NONWORKINGDAYS_YEARPAGE_ROUTE) {

        NonWorkingDaysYearPageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            nonWorkingDaysViewModel
        )
    }
}