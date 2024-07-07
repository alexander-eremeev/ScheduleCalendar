package com.childmathematics.android.shiftschedule.ui.schedules.schedule01.summingpage

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
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.childmathematics.android.basement.lib.composecalendar.CalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.ui.AppViewModelProvider
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.Schedule01PageViewModel
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.getShift01
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.getShift01MonthDate
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.getShift01MonthDateDays
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Schedule01SummingPageScreen(
        modifier: Modifier = Modifier,
        onBackClick: () -> Unit,
//        state: CalendarState<DynamicSelectionState>,
        schedule01PageViewModel: Schedule01PageViewModel = viewModel()
//                viewModel: Schedule01PageViewModel = viewModel()
//        viewModel: Schedule01PageViewModel = viewModel(factory = AppViewModelProvider.Factory)

) {
    if (BuildConfig.DEBUG) {
        Log.d(
            "Schedule01",
            "+++Schedule01SummingPageScreen: selection.lastIndex =" +
//                                    state.selectionState.selection.lastIndex
                    schedule01PageViewModel.vmselection.lastIndex
        )
    }

/*
    ScheduleCalendarTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
//        val schedule01PageUiState by schedule01PageViewModel.schedule01PageUiState.collectAsState()
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
                    if (BuildConfig.DEBUG) {
                        Log.d(
                            "Schedule01",
                            "+++Schedule01SummingPageScreen: selection.lastIndex =" +
//                                    state.selectionState.selection.lastIndex
                                    schedule01PageViewModel.vmselection.lastIndex
                        )
                    }
                    if (schedule01PageViewModel.vmselection.isNotEmpty()) {
                        if (BuildConfig.DEBUG) {
                            //-------------------------
                            Log.d(
                                "Schedule01", "+++Schedule01SummingPageScreen: vmselection.lastIndex =" +
                                        schedule01PageViewModel.vmselection.lastIndex
                            )
                            for (i in  schedule01PageViewModel.vmselection.lastIndex downTo 0 step 1) {
                                Log.d(
                                    "Schedule01", "+++Schedule01SummingPageScreen: selected " +
                                            schedule01PageViewModel.vmselection[i].dayOfMonth + "/"
                                            +  schedule01PageViewModel.vmselection[i].monthValue + "/" +
                                            schedule01PageViewModel.vmselection[i].year
                                )
                            }
                        }
                    }

                    if (schedule01PageViewModel.vmselection.size == 0) {
                        if (BuildConfig.DEBUG) {
                            Log.d(
                                "Schedule01", "+++Schedule01SummingPageScreen: vmselection.lastIndex =" +
                                        schedule01PageViewModel.vmselection.lastIndex
                            )
                            Log.d(
                                "Schedule01", "+++++Schedule01SummingPage: "+
                                        stringResource(R.string.schedule01_NoSelectedDays)
                            )
                        }
                    }

                    DialogSchedule01(schedule01PageViewModel.vmselection, onDismiss =true)
                }
            },
        )
    }
    */
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
fun DialogSchedule01(selection: List<LocalDate>, onDismiss: Boolean) {

    /*
      Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
      ) {

     */
    if (selection.isNotEmpty()) {
        Toast.makeText(
            LocalContext.current,
            "Dialog Есть выделенные даты для расчета!! ",
            Toast.LENGTH_LONG
        ).show()

    }

    if (selection.size == 0) {
        Toast.makeText(
            LocalContext.current,
//            stringResource(R.string.schedule01_NoSelectedDays),
                    "Dialog Нет выделенных дат для расчета!! ",
            Toast.LENGTH_LONG
        ).show()
    }

    Surface(tonalElevation = 8.dp, shape = RoundedCornerShape(12.dp)) {
        Column(
            modifier = Modifier
                //====================================================
                // фиксация нажатия экрана для сдвига паказа рекламы
                .pointerInput(Unit) {
                    /*
                    detectTapAndPressUnconsumed(onTap = {
                        Log.d(YANDEX_MOBILE_ADS_TAG, "DialogSchedule01 Interstitial:select date TAP")
                        yaAdsInterstutialTimerOff()  //реклама через 180 cек  durationNoPushTastaturAds
                    })

                     */
                }
                //--------------------------------------------------

                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
//          .width(400.dp)

                .wrapContentHeight()
                .background(Color.White)
                .padding(8.dp)
        ) {

            Text(
                text = "Расчет рабочих часов\nдля выделенных дат:",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
//          modifier = Modifier.padding(8.dp)
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
                    text = "\nС начала месяца:\n\t"
                            +String.format("%4d",(getShift01MonthDateDays(selection[0])))
                            +"\tраб.дн.\t"
                            + String.format("%4d",(getShift01MonthDate (selection[0]).toInt()))
                            +"\tчас."
                    ,

                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
//          modifier = Modifier.padding(8.dp)
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
//          textAlign= Center,
                        fontSize = 20.sp,    )
                    //-------------------------getShift01Date1Date2 (date1: LocalDate,date2: LocalDate------------------
                    Text(
                        text = "\tОтработано:\n\t"
                                +String.format("%4d",(getShift01Date1Date2Days(selection[0],
                            selection[selection.lastIndex])))
                                +"\tраб.дн.\t"
//                      + String.format("%4d",(getShift01MonthDate (selection[0],selection[selection.lastIndex]))
                                +String.format("%5d", getShift01Date1Date2 (selection[0],
                            selection[selection.lastIndex]).toInt())
                                +"\tчас."
                        ,
                        fontSize = 15.sp,    )
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
            //     DialogButtonOK(onDismiss)
        }
    }
//  }
}
//====================================================================
// расчет основного времени между выбранной даты по номеру бригады с начала месяца
//==============================================
fun getShift01Date1Date2 (date1: LocalDate, date2: LocalDate):Double {
//  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1).minusDays(1)
    var dateforCalc: LocalDate= date2

    var summ: Double = getShift01(dateforCalc)
    for (i in dateforCalc.toEpochDay()-date1.toEpochDay() downTo 1 step 1) {
        summ += getShift01(dateforCalc.minusDays(i.toLong()))
    }
    return summ
}
//====================================================================
// расчет основного времени между выбранной даты по номеру бригады с начала месяца
//==============================================
fun getShift01Date1Date2Days (date1: LocalDate, date2: LocalDate):Int {
//  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1).minusDays(1)
    var dateforCalc: LocalDate= date2

    var summDays: Int
    if (getShift01(dateforCalc) >0.0) summDays  =1 else summDays  =0
    for (i in dateforCalc.toEpochDay()-date1.toEpochDay() downTo 1 step 1) {
        if (getShift01(dateforCalc.minusDays(i.toLong())) >0.0)
            summDays  +=1


    }
    return summDays
}
