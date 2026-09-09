package com.childmathematics.android.shiftschedule.presentation.ui.schedules.schedule01

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.shiftschedule.presentation.ui.ScheduleViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.NonWorkingDaysViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.year.navigateToNonWorkingDaysYearPageGraph
import com.childmathematics.android.shiftschedule.presentation.ui.schedules.schedule01.summingpage.navigateToSchedule01SummingPageGraph


internal const val SCHEDULE01_PAGE_ROUTE = "schedule01Page"
internal fun NavGraphBuilder.schedule01PageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    onOpenDrawer: Boolean,
    scheduleViewModel : ScheduleViewModel,
    nonWorkingDaysViewModel: NonWorkingDaysViewModel
  ) {
    composable(route = SCHEDULE01_PAGE_ROUTE) {
        Schedule01PageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            onOpenDrawer = onOpenDrawer,openDrawer =openDrawer,
            navigateToSchedule01SummingPage = { navController.navigateToSchedule01SummingPageGraph()},
            { navController.navigateToNonWorkingDaysYearPageGraph()},
            scheduleViewModel,    nonWorkingDaysViewModel
        )
    }
}
