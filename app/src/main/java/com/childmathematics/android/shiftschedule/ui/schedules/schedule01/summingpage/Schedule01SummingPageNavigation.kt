package com.childmathematics.android.shiftschedule.ui.schedules.schedule01.summingpage

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.shiftschedule.ui.ScheduleViewModel


internal const val SCHEDULE01_SUMMINGPAGE_ROUTE = "schedule01SummingPage"
internal fun NavGraphBuilder.schedule01SummingPageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
//                state: CalendarState<DynamicSelectionState>
    scheduleViewModel: ScheduleViewModel,

    ) {
    composable(route = SCHEDULE01_SUMMINGPAGE_ROUTE) {

        Schedule01SummingPageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            scheduleViewModel
        )
    }
}