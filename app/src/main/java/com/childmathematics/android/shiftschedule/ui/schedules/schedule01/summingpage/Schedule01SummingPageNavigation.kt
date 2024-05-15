package com.childmathematics.android.shiftschedule.ui.schedules.schedule01.summingpage

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.basement.lib.composecalendar.CalendarState
import com.childmathematics.android.basement.lib.composecalendar.rememberSelectableCalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionMode


internal const val SCHEDULE01_SUMMINGPAGE_ROUTE = "schedule01SummingPage"
internal fun NavGraphBuilder.schedule01SummingPageScreen(
                navController: NavHostController,
                modifier: Modifier = Modifier,
//                state: CalendarState<DynamicSelectionState>
) {
    composable(route = SCHEDULE01_SUMMINGPAGE_ROUTE) {
/*
        state = rememberSelectableCalendarState(
//.        onSelectionChanged = viewModel::onSelectionChanged, //SelectionMode
//        confirmSelectionChange = viewModel::onSelectionChanged, //SelectionMode

            initialSelectionMode = SelectionMode.Period,
        )

 */

        Schedule01SummingPageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
//            state
        )
    }
}