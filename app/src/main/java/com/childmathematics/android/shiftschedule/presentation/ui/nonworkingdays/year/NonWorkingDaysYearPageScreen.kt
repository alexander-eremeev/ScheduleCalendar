package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.year

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.childmathematics.android.basement.lib.composecalendar.CalendarState
import com.childmathematics.android.basement.lib.composecalendar.rememberSelectableCalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.presentation.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.NonWorkingDaysViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.uimodels.NonWorkingDaysViewIntent
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util.HoliDaysHeader
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util.HoliDaysItem
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util.NonWorkingDaysHeader
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util.NonWorkingDaysItem
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
// ----------------------------------------------------------------------------------
                        val listState = rememberLazyListState()
                        LazyColumn(
                            state = listState,
                            modifier = Modifier
                                //                       .fillMaxSize()
                                .height( (LocalConfiguration.current.screenHeightDp*0.7). dp)

                                .padding(horizontal = 0.dp),

                            horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                            itemsIndexed(
                                items = nonWorkingDaysViewState.holiDaysYear,
                                key = { index, item ->
                                    index
                                },
                            ) { index, item ->
                                HoliDaysItem(nonWorkingDaysViewState.holiDaysYear[index] )
                            }
                        }
// ----------------------------------------------------------------------------------
                    }
                    if (nonWorkingDaysViewState.nonWorkingDaysYear.count()>0) {
// ----------------------------------------------------------------------------------
                        val listState = rememberLazyListState()
                        LazyColumn(
                            state = listState,
                            modifier = Modifier
                                .height( (LocalConfiguration.current.screenHeightDp*0.2). dp)
                                .padding(horizontal = 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            //items
                            itemsIndexed(
                                items = nonWorkingDaysViewState.nonWorkingDaysYear,
                                key = { index, item ->
                                    index
                                },
                            ) { index, item ->
                                NonWorkingDaysItem(nonWorkingDaysViewState.nonWorkingDaysYear[index])
                            }
                        }
// ----------------------------------------------------------------------------------
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
