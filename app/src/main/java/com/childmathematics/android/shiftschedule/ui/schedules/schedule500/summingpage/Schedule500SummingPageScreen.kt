package com.childmathematics.android.shiftschedule.ui.schedules.schedule500.summingpage

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.ui.ScheduleViewModel
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.getShift01
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.getShift01MonthDate
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.getShift01MonthDateDays
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.DialogSchedule500
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.getShift500
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.getShift500Date1Date2
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.getShift500Date1Date2Night
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.getShift500MonthDate
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.getShift500MonthDateNight
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.getShift500Night
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.getShift500Text
import com.childmathematics.android.shiftschedule.util.DialogButtonOK
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Schedule500SummingPageScreen(
        modifier: Modifier = Modifier,
        onBackClick: () -> Unit,
//        state: CalendarState<DynamicSelectionState>,
        scheduleViewModel: ScheduleViewModel = viewModel()
//                viewModel: Schedule01PageViewModel = viewModel()
//        viewModel: Schedule01PageViewModel = viewModel(factory = AppViewModelProvider.Factory)

) {
    /*
    if (BuildConfig.DEBUG) {
        Log.d(
            "Schedule01",
            "+++Schedule01SummingPageScreen: selection.lastIndex =" +
//                                    state.selectionState.selection.lastIndex
                    schedule01PageViewModel.vmselection.lastIndex
        )
    }

     */


    ScheduleCalendarTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
// val schedule01PageUiState by schedule01PageViewModel.schedule01PageUiState.collectAsState()
        val scheduleUiState by scheduleViewModel.scheduleUiState.collectAsStateWithLifecycle()
         Scaffold(
            modifier = modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                Schedule500SummingPageTopAppBar(
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
                            "Schedule500",
                            "+++Schedule500SummingPageScreen: selection.lastIndex =" +
//                                    state.selectionState.selection.lastIndex
                                    scheduleUiState.lastIndex
                        )
                    }
                    if (scheduleUiState.isNotEmpty()) {
                        if (BuildConfig.DEBUG) {
                            //-------------------------
                            Log.d(
                                "Schedule500", "+++Schedule500SummingPageScreen: scheduleUiState.lastIndex =" +
                                        scheduleUiState.lastIndex
                            )
                            for (i in  scheduleUiState.lastIndex downTo 0 step 1) {
                                Log.d(
                                    "Schedule500", "+++Schedule500SummingPageScreen: selected " +
                                            scheduleUiState[i].dayOfMonth + "/"
                                            +  scheduleUiState[i].monthValue + "/" +
                                            scheduleUiState[i].year
                                )
                            }
                        }
                    }

                    if (scheduleUiState.size == 0) {
                        if (BuildConfig.DEBUG) {
                            Log.d(
                                "Schedule500", "+++Schedule500SummingPageScreen: scheduleUiState.lastIndex =" +
                                        scheduleUiState.lastIndex
                            )
                            Log.d(
                                "Schedule01", "+++++Schedule500SummingPage: "+
                                        stringResource(R.string.schedule01_NoSelectedDays)
                            )
                        }
                    }

                    DialogSchedule500(scheduleUiState)
                }
            },
        )
    }

}
//=================================
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Schedule500SummingPageTopAppBar(
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
/*
@Composable
fun DialogSchedule500(selection: List<LocalDate>, onDismiss: Boolean) {

    /*
      Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
      ) {

     */
    /*
    if (selection.isNotEmpty()) {
        Toast.makeText(
            LocalContext.current,
            "Dialog Есть выделенные даты для расчета!! ",
            Toast.LENGTH_LONG
        ).show()

    }

     */

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

 */
//===================================================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DialogSchedule500(selection: List<LocalDate>) {

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
                            Log.d(YANDEX_MOBILE_ADS_TAG, "DialogSchedule500 Interstitial: TAP")
                            yaAdsInterstutialTimerOff()  //реклама через 180 cек  durationNoPushTastaturAds
                        })

                         */
                    }
                    //--------------------------------------------------

                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
//          .width(400.dp)

                    .wrapContentHeight()
                    .background(androidx.compose.ui.graphics.Color.White)
                    .padding(8.dp)
            ) {

                Text(
                    text = "Расчет рабочих часов\nдля выделенных дат:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
//          modifier = Modifier.padding(8.dp)
                    modifier = Modifier
                        .align(CenterHorizontally),
                )
                if (selection.size ==1 ) {
                    //-------------------------------------------------------
                    Text(
                        text = "\n"
                                +selection[0].dayOfMonth.toString()+"."
                                +selection[0].monthValue.toString()+"."
                                +selection[0].year.toString()
                        ,
                        modifier = Modifier.align(CenterHorizontally) ,
                        fontSize = 20.sp,    )

                    Text(
                        text = "Бригада 1:"
                                +String.format("%3d", getShift500 (selection[0],1).toInt())
                                +" ("
                                +String.format("%1d", getShift500Night (selection[0],1).toInt())
                                +") час"
//                +"\t"
                                +String.format("%10s", getShift500Text (selection[0],1))
                        ,
                        fontSize = 15.sp,    )


                    Text(
                        text = "Бригада 2:"
                                +String.format("%3d", getShift500 (selection[0],2).toInt())
                                +" ("
                                +String.format("%1d", getShift500Night (selection[0],2).toInt())
                                +") час"
//                +"\t"
                                +String.format("%10s", getShift500Text (selection[0],2))
                        ,
                        fontSize = 15.sp,
                    )


                    Text(
                        text = "Бригада 3:"
                                +String.format("%3d", getShift500 (selection[0],3).toInt())
                                +" ("
                                +String.format("%1d", getShift500Night (selection[0],3).toInt())
                                +") час"
//                +"\t"
                                +String.format("%10s", getShift500Text (selection[0],3))
                        ,

                        fontSize = 15.sp,
                    )
                    Text(
                        text = "Бригада 4:"
                                +String.format("%3d", getShift500 (selection[0],4).toInt())
                                +" ("
                                +String.format("%1d", getShift500Night (selection[0],4).toInt())
                                +") час"
//                    +"\t"
                                +String.format("%10s", getShift500Text (selection[0],4))
                        ,

                        fontSize = 15.sp,
                    )
//------------------------------------------------------------------------------------------------------------------------------

                    Text(
                        text = "\nС начала месяца:\n",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
//          modifier = Modifier.padding(8.dp)
                        modifier = Modifier
                            .align(CenterHorizontally),
                    )
                    Text(
                        text = "\tБригада 1:"
                                +String.format("%5d", getShift500MonthDate (selection[0],1).toInt())
                                +" ("
                                +String.format("%5d", getShift500MonthDateNight (selection[0],1).toInt())
                                +") час"
                        ,
                        fontSize = 15.sp,    )


                    Text(
                        text = "\tБригада 2:"
                                +String.format("%5d", getShift500MonthDate (selection[0],2).toInt())
                                +" ("
                                +String.format("%5d", getShift500MonthDateNight (selection[0],2).toInt())
                                +") час"
                        ,
                        fontSize = 15.sp,
                        //style = MaterialTheme.typography.h6,
                    )


                    Text(
                        text = "\tБригада 3:"
                                +String.format("%5d", getShift500MonthDate(selection[0],3).toInt())
                                +" ("
                                +String.format("%5d", getShift500MonthDateNight (selection[0],3).toInt())
                                +") час"
                        ,

                        fontSize = 15.sp,
                    )
                    Text(                                   //getShift500MonthDate (datecalc: LocalDate,nBrig: Int):Double
                        text = "\tБригада 4:"
                                +String.format("%5d", getShift500MonthDate (selection[0],4).toInt())
                                +" ("
                                +String.format("%5d", getShift500MonthDateNight (selection[0],4).toInt())
                                +") час"
                        ,

                        fontSize = 15.sp,
                    )

//========================================================================
                    Text(
                        text = "\n\tПояснение:\tв скобках - ночные часы"
                        ,
                        fontSize = 15.sp,
                    )

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
                            modifier = Modifier.align(CenterHorizontally) ,
//          textAlign= Center,
                            fontSize = 20.sp,    )
                        //--------------------------------------------getShift500Date1Date2 (date1: LocalDate,date2: LocalDate------------------
                        Text(
                            text = "\tБригада 1:"
                                    +String.format("%5d",
                                getShift500Date1Date2 (selection[0],selection[selection.lastIndex],1).toInt())
                                    +" ("
                                    +String.format("%5d",
                                getShift500Date1Date2Night (selection[0],selection[selection.lastIndex],1).toInt())
                                    +") час"
                            ,
                            fontSize = 15.sp,    )


                        Text(
                            text = "\tБригада 2:"
//                +"\t/\tРабочих:\t"
                                    +String.format("%5d",
                                getShift500Date1Date2 (selection[0],selection[selection.lastIndex],2).toInt())
//                +"Ночных:\t"
                                    +" ("
                                    +String.format("%5d",
                                getShift500Date1Date2Night (selection[0],selection[selection.lastIndex],2).toInt())
                                    +") час"
                            ,
                            fontSize = 15.sp,
                        )


                        Text(
                            text = "\tБригада 3:"
                                    +String.format("%5d",
                                getShift500Date1Date2 (selection[0],selection[selection.lastIndex],3).toInt())
                                    +" ("
                                    +String.format("%5d",
                                getShift500Date1Date2Night (selection[0],selection[selection.lastIndex],3).toInt())
                                    +") час"
                            ,
                            fontSize = 15.sp,
                        )
                        Text(                                   //getShift500MonthDate (datecalc: LocalDate,nBrig: Int):Double
                            text = "\tБригада 4:"
                                    +String.format("%5d",
                                getShift500Date1Date2 (selection[0],selection[selection.lastIndex],4).toInt())
                                    +" ("
                                    +String.format("%5d",
                                getShift500Date1Date2Night (selection[0],selection[selection.lastIndex],4).toInt())
                                    +") час"
                            ,

                            fontSize = 15.sp,
                        )
                        Text(
                            text = "\n\tПояснение:\tв скобках - ночные часы"
                            ,
                            fontSize = 15.sp,
                        )

                        //=======================================================
                    } else {
                        Text(
                            text = "\n\nСлишком длинный промежуток\nмежду выделенными датами!!"
                            ,
                            modifier = Modifier.align(CenterHorizontally) ,
                            color = androidx.compose.ui.graphics.Color.Red ,
                            fontSize = 20.sp,
                        )

                    }
                Spacer(modifier = Modifier.height(4.dp))
//                DialogButtonOK(onDismiss)
            }
        }
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
