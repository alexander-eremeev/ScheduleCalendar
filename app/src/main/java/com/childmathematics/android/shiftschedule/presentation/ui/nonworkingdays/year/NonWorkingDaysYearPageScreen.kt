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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.presentation.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.NonWorkingDaysViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NonWorkingDaysYearPageScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    nonWorkingDaysViewModel:  NonWorkingDaysViewModel
) {

    ScheduleCalendarTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
// val schedule01PageUiState by schedule01PageViewModel.schedule01PageUiState.collectAsState()
        val  nonWorkingDaysUiState by  nonWorkingDaysViewModel. nonWorkingDaysUiState.collectAsStateWithLifecycle()
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
                    Text(
                        text = "\n\nЭкран нерабочих дней года\nОк!!"
                        ,
                        modifier = Modifier.align(Alignment.CenterHorizontally) ,
                        color = Color.Red ,
                        fontSize = 20.sp,
                    )

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
