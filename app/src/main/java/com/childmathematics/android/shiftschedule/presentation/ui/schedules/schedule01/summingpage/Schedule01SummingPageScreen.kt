package com.childmathematics.android.shiftschedule.presentation.ui.schedules.schedule01.summingpage

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BackHand
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.presentation.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.presentation.ui.ScheduleViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.NonWorkingDaysViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.uimodels.NonWorkingDaysViewState
import com.childmathematics.android.shiftschedule.presentation.ui.schedules.schedule01.getShift01
import com.childmathematics.android.shiftschedule.presentation.ui.schedules.schedule01.getWorkingDay
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Schedule01SummingPageScreen(
        modifier: Modifier = Modifier,
        onBackClick: () -> Unit,
        scheduleViewModel: ScheduleViewModel = viewModel(),
        nonWorkingDaysViewModel: NonWorkingDaysViewModel

) {
    val nonWorkingDaysViewState by nonWorkingDaysViewModel.viewState.collectAsStateWithLifecycle()

    ScheduleCalendarTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        val scheduleUiState by scheduleViewModel.scheduleUiState.collectAsStateWithLifecycle()
         Scaffold(
            modifier = modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                Schedule01SummingPageTopAppBar(
                    onBackClick, scrollBehavior,
                )
            },
            content ={ padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
                        DialogSchedule01(scheduleUiState,nonWorkingDaysViewState)
                    }
                     if (scheduleUiState.isEmpty()) {
                         Toast.makeText(
                             LocalContext.current,
                             stringResource(R.string.schedule01_NoSelectedDays),
                             Toast.LENGTH_LONG
                         ).show()
                     }
                }
        )
    }
}
//=================================
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Schedule01SummingPageTopAppBar(
                onBackClick: () -> Unit,
                scrollBehavior: TopAppBarScrollBehavior,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.schedule01_SummingSelectedDays))
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
//====================================================================
@Composable
fun DialogSchedule01(selection: List<LocalDate>,nonWorkingDaysViewState : NonWorkingDaysViewState) {

    Surface(tonalElevation = 8.dp, shape = RoundedCornerShape(12.dp)) {
        Column(
            modifier = Modifier
                //====================================================
                // фиксация нажатия экрана для сдвига паказа рекламы
                .pointerInput(Unit) {
                 }
                //--------------------------------------------------
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)
                .padding(8.dp)
        ) {
            Text(
                text = "Расчет рабочих часов\nдля выделенных дат:",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            )
            if (selection.size ==1 ) {
                //-------------------------------------------------------
                Text(
                    text = "\n"
                            +selection[0].dayOfMonth.toString()+"."
                            +selection[0].monthValue.toString()+"."
                            +selection[0].year.toString()
                    ,
                    modifier = Modifier.align(Alignment.CenterHorizontally) ,
                    fontSize = 20.sp,    )
//----------------------------------------------------------------------------------------------
                Text(
                    text = "\tОтработано:\n\t"
                            +String.format("%4d \tраб.дн. \t",(getDays01Date1Date2(selection[0],
                        selection[selection.lastIndex],nonWorkingDaysViewState)))
                            +String.format("%5d час.", getHours01Date1Date2 (selection[0],
                        selection[selection.lastIndex],nonWorkingDaysViewState))
                    ,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    )
//---------------------------------------------------------------------------------------------
                Text(
                    text = "\nС начала месяца:\n\t"
                            +String.format("%4d \tраб.дн. \t",(getDays01Date1Date2(selection[0].minusDays(
                        (selection[0].dayOfMonth-1).toLong()
                            ),selection[selection.lastIndex],nonWorkingDaysViewState)))
                            +String.format("%5d час.", getHours01Date1Date2 (selection[0].minusDays(
                        (selection[0].dayOfMonth-1).toLong()),
                                selection[selection.lastIndex],nonWorkingDaysViewState))
                        ,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                )
                Spacer(modifier = Modifier.height(20.dp))
                //=====================================================
            } else
                if (selection.size >1 &&
                    selection[selection.lastIndex].toEpochDay()-selection[0].toEpochDay()< 70) {
                    //------------------------------------------------
                    Text(
                        text = "\n"
                                +selection[0].dayOfMonth.toString()+"."
                                +selection[0].monthValue.toString()+"."
                                +selection[0].year.toString()+"\t\t--\t\t"
                                +selection[selection.lastIndex].dayOfMonth.toString()+"."
                                +selection[selection.lastIndex].monthValue.toString()+"."
                                +selection[selection.lastIndex].year.toString()
                                +"\n"
                        ,
                        modifier = Modifier.align(Alignment.CenterHorizontally) ,
                        fontSize = 20.sp,    )
                    //-------------------------getShift01Date1Date2 (date1: LocalDate,date2: LocalDate------------------
                    Text(
                        text = "\tОтработано:\n\t"
                                +String.format("%4d \tраб.дн. \t",(getDays01Date1Date2(selection[0],
                            selection[selection.lastIndex],nonWorkingDaysViewState)))
                                +String.format("%5d час.", getHours01Date1Date2 (selection[0],
                            selection[selection.lastIndex],nonWorkingDaysViewState))
                        ,
                        fontSize = 14.sp,    )
                } else {
                    Text(
                        text = "\n\nСлишком длинный промежуток\nмежду выделенными датами!!"
                        ,
                        modifier = Modifier.align(Alignment.CenterHorizontally) ,
                        color = Color.Red ,
                        fontSize = 20.sp,
                    )

                }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
//====================================================================
@Composable
fun getHours01Date1Date2 (date1: LocalDate, date2: LocalDate,
                           nonWorkingDaysViewState : NonWorkingDaysViewState):Int {
    var dateforCalc: LocalDate= date2

    var summ: Int = 0
    //getWorkingDay (dateforCalc.year,dateforCalc.month.value,dateforCalc.dayOfMonth,nonWorkingDaysViewState )
    when (getWorkingDay (dateforCalc.year,dateforCalc.month.value,dateforCalc.dayOfMonth,nonWorkingDaysViewState )) {
        3,5,7-> {       //рабочая СБ //рабочая СБ , //рабочая день
            when (nonWorkingDaysViewState.holiDaysOnlyDateYear.contains( dateforCalc.plusDays(1).toString())) {
                true -> {
                    summ +=7
                }
                else -> {
                    summ +=8
                }
            }
        }
        else -> null
    }

    for (i in dateforCalc.toEpochDay()-date1.toEpochDay() downTo 1 step 1) {
        when ( getWorkingDay (dateforCalc.minusDays(i).year,
            dateforCalc.minusDays(i).monthValue,
            dateforCalc.minusDays(i).dayOfMonth,nonWorkingDaysViewState )){
            3,5,7-> {       //рабочая СБ //рабочая СБ , //рабочая день
                when (nonWorkingDaysViewState.holiDaysOnlyDateYear.contains(
                    dateforCalc.minusDays(i).plusDays(1).toString())) {
                    true -> {
                        summ +=7
                    }
                    else -> {
                        summ +=8
                    }
                }
            }
            else -> null
        }
    }
    return summ
}
//==============================================
@Composable
fun getDays01Date1Date2 (date1: LocalDate, date2: LocalDate,
                           nonWorkingDaysViewState : NonWorkingDaysViewState):Int {
    var dateforCalc: LocalDate= date2

    var sumDays: Int = 0
    when (getWorkingDay (dateforCalc.year,dateforCalc.month.value,dateforCalc.dayOfMonth,nonWorkingDaysViewState )) {
        3,5,7-> {       //рабочая СБ //рабочая СБ , //рабочая день
            sumDays += 1
        }
        else -> null
    }
    for (i in dateforCalc.toEpochDay()-date1.toEpochDay() downTo 1 step 1) {
        when ( getWorkingDay (dateforCalc.minusDays(i).year,
            dateforCalc.minusDays(i).monthValue,
            dateforCalc.minusDays(i).dayOfMonth,nonWorkingDaysViewState )){
            3,5,7-> {       //рабочая СБ //рабочая СБ , //рабочая день

                sumDays += 1
            }
            else -> null
         }
    }
    return sumDays
}
