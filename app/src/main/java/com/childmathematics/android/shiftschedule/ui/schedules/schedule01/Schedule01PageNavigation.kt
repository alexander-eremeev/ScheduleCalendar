package com.childmathematics.android.shiftschedule.ui.schedules.schedule01

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.basement.lib.composecalendar.CalendarState
import com.childmathematics.android.basement.lib.composecalendar.rememberSelectableCalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionMode
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.summingpage.navigateToSchedule01SummingPageGraph


internal const val SCHEDULE01_PAGE_ROUTE = "schedule01Page"
internal fun NavGraphBuilder.schedule01PageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit ,
    onOpenDrawer: Boolean ,
//    state: CalendarState<DynamicSelectionState>,
    /*
    navigateToHelpSchedule01Page: () -> Unit,
    navigateToHelpGraphicsPage: () -> Unit,
    navigateToHelpAboutPage: () -> Unit

     */
  ) {
    composable(route = SCHEDULE01_PAGE_ROUTE) {
        /*
        val state = rememberSelectableCalendarState(
//.        onSelectionChanged = viewModel::onSelectionChanged, //SelectionMode
//        confirmSelectionChange = viewModel::onSelectionChanged, //SelectionMode

            initialSelectionMode = SelectionMode.Period,
        )

         */


        Schedule01PageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            onOpenDrawer = onOpenDrawer,openDrawer =openDrawer,
            navigateToSchedule01SummingPage = { navController.navigateToSchedule01SummingPageGraph()},
//            state,

            /*
            navigateToHelpMainPage ,
            navigateToHelpGraphicsPage,
            navigateToHelpAboutPage

             */
        )
    }
}
