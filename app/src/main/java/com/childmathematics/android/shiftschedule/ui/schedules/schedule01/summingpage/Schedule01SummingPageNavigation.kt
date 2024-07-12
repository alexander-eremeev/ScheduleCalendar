package com.childmathematics.android.shiftschedule.ui.schedules.schedule01.summingpage

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.basement.lib.composecalendar.CalendarState
import com.childmathematics.android.basement.lib.composecalendar.rememberSelectableCalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionMode
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.Schedule01PageViewModel


internal const val SCHEDULE01_SUMMINGPAGE_ROUTE = "schedule01SummingPage"
internal fun NavGraphBuilder.schedule01SummingPageScreen(
                navController: NavHostController,
                modifier: Modifier = Modifier,
//                state: CalendarState<DynamicSelectionState>
                schedule01PageViewModel: Schedule01PageViewModel,

) {
    composable(route = SCHEDULE01_SUMMINGPAGE_ROUTE) {

        Schedule01SummingPageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            schedule01PageViewModel
        )
    }
}