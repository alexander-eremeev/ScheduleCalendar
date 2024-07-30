package com.childmathematics.android.shiftschedule.ui.schedules.schedule500

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.shiftschedule.ui.ScheduleViewModel
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.summingpage.navigateToSchedule500SummingPageGraph


internal const val SCHEDULE500_PAGE_ROUTE = "schedule500Page"
internal fun NavGraphBuilder.schedule500PageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    onOpenDrawer: Boolean,
    scheduleViewModel : ScheduleViewModel,
    ) {
    composable(route = SCHEDULE500_PAGE_ROUTE) {
        Schedule500PageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            onOpenDrawer = onOpenDrawer,openDrawer =openDrawer,
            navigateToSchedule500SummingPage = { navController.navigateToSchedule500SummingPageGraph()},
            scheduleViewModel,
        )
    }
}
