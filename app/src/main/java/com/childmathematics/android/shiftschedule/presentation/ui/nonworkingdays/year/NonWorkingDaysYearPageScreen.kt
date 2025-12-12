package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.year

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BackHand
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.childmathematics.android.basement.lib.composecalendar.rememberSelectableCalendarState
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.presentation.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.NonWorkingDaysViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.uimodels.NonWorkingDaysViewIntent
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util.HoliDaysHeader
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util.NonWorkingDaysHeader
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.year.util.HolyDaysYearListScreen
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.year.util.NonWorkingDaysYearListScreen
import com.childmathematics.android.shiftschedule.presentation.util.SnackbarEffect
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NonWorkingDaysYearPageScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
//    state: CalendarState<DynamicSelectionState>,
    nonWorkingDaysViewModel:  NonWorkingDaysViewModel
) {
    //-------------------------------------------------------------------------
//    val state: CalendarState<DynamicSelectionState>,
    var state = rememberSelectableCalendarState()
    val year = state.monthState.currentMonth.year

    nonWorkingDaysViewModel.handleIntent(NonWorkingDaysViewIntent.LoadNonWorkingDaysYear(year))
    nonWorkingDaysViewModel.handleIntent(NonWorkingDaysViewIntent.LoadHoliDaysYear(year))

    val nonWorkingDaysViewState by nonWorkingDaysViewModel.viewState.collectAsStateWithLifecycle()

    // State to manage Snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    // Collect snackbar events and show snackbar
    LaunchedEffect(nonWorkingDaysViewModel) {
        nonWorkingDaysViewModel.effectFlow.collectLatest { effect ->
            if (effect is SnackbarEffect.ShowSnackbar) {
                val result = snackbarHostState.showSnackbar(
                    message = effect.message,
                    actionLabel = effect.actionLabel
                )
                if (result == SnackbarResult.ActionPerformed && effect.actionLabel == "Undo") {
//                    viewModel.handleIntent(Intent.UndoDelete)
                }
            }
        }
    }
    //-------------------------------------------------------------------------------

    ScheduleCalendarTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        Scaffold(
            modifier = modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                NonWorkingDaysYearPageTopAppBar(
                    onBackClick, scrollBehavior,
                )
            },
            content ={ padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    if (nonWorkingDaysViewState.holiDaysYear.count()>0) {
                        HoliDaysHeader()
                        HolyDaysYearListScreen(nonWorkingDaysViewModel)
                    }
                    if (nonWorkingDaysViewState.nonWorkingDaysYear.count()>0) {
                        NonWorkingDaysHeader()
                        NonWorkingDaysYearListScreen(nonWorkingDaysViewModel)
                    }

                }
            },
        )
    }
}
//=================================
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun NonWorkingDaysYearPageTopAppBar(
    onBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.nonWorkingDays_Year))
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Filled.BackHand, stringResource(id = R.string.back_button))
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = Modifier.fillMaxWidth()
    )
}
