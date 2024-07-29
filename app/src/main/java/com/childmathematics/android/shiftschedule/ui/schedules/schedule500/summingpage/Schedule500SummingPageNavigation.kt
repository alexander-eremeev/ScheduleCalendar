package com.childmathematics.android.shiftschedule.ui.schedules.schedule500.summingpage

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.shiftschedule.ui.ScheduleViewModel


internal const val SCHEDULE500_SUMMINGPAGE_ROUTE = "schedule500SummingPage"
internal fun NavGraphBuilder.schedule01SummingPageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
//                state: CalendarState<DynamicSelectionState>
    scheduleViewModel: ScheduleViewModel,

    ) {
    composable(route = SCHEDULE500_SUMMINGPAGE_ROUTE) {

        Schedule500SummingPageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            scheduleViewModel
        )
    }
}