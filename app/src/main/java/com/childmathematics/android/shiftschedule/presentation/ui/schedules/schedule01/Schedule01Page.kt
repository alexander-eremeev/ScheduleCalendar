package com.childmathematics.android.shiftschedule.presentation.ui.schedules.schedule01


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.i18n.DateTimeFormatter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.childmathematics.android.basement.lib.composecalendar.SelectableCalendar
import com.childmathematics.android.basement.lib.composecalendar.day.DayState
import com.childmathematics.android.basement.lib.composecalendar.rememberSelectableCalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionMode
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.presentation.ui.ScheduleViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.NonWorkingDaysViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.uimodels.NonWorkingDaysViewIntent
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.uimodels.NonWorkingDaysViewState
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util.HoliDaysListScreen
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util.NonWorkingDaysListScreen
import com.childmathematics.android.shiftschedule.presentation.util.SnackbarEffect
import com.childmathematics.android.shiftschedule.util.bannerHightMin
import com.childmathematics.android.shiftschedule.util.bannerHightPlus
import com.childmathematics.android.shiftschedule.util.bannerHightWithVideoMin
import com.childmathematics.android.shiftschedule.util.nonScaledSp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate

@ExperimentalCoroutinesApi
@Composable
fun Schedule01Page(
                   scheduleViewModel: ScheduleViewModel = viewModel(),
                   nonWorkingDaysViewModel: NonWorkingDaysViewModel
)
{
    var state = rememberSelectableCalendarState(
        initialSelectionMode = SelectionMode.Period,
    )
    val year = state.monthState.currentMonth.year
    val month = state.monthState.currentMonth.monthValue
//-------------------------------------------------------------------------
    val nonWorkingDaysViewState by nonWorkingDaysViewModel.viewState.collectAsStateWithLifecycle()
    when (nonWorkingDaysViewState.month == month ){
        //true -> null
        else -> {
            nonWorkingDaysViewModel.handleIntent(NonWorkingDaysViewIntent.LoadHoliDays(year,month))
            nonWorkingDaysViewModel.handleIntent(NonWorkingDaysViewIntent.LoadNonWorkingDays(year,month))
        }
    }
    when (nonWorkingDaysViewState.year == year){
        //true -> null
        else -> {
            nonWorkingDaysViewModel.handleIntent(NonWorkingDaysViewIntent.LoadHoliDaysOnlyDateYear(year))
            nonWorkingDaysViewModel.handleIntent(NonWorkingDaysViewIntent.LoadNonWorkingDaysOnlyDateYear(year))
            nonWorkingDaysViewModel.handleIntent(NonWorkingDaysViewIntent.LoadNonWorkingDaysOnlyWorkDateYear(year))
            nonWorkingDaysViewModel.handleIntent(NonWorkingDaysViewIntent.LoadHoliDaysYear(year))
            nonWorkingDaysViewModel.handleIntent(NonWorkingDaysViewIntent.LoadNonWorkingDaysYear(year))
        }
    }
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
    var changeDp: Dp
    //------------------------
    var changeHightDp: Int
    var screenHeightDp: Int

    screenHeightDp = LocalConfiguration.current.screenHeightDp
    changeHightDp = screenHeightDp
    if( BuildConfig.YaAdsEnable) {
        if (screenHeightDp > 800)
            changeHightDp = screenHeightDp- bannerHightWithVideoMin - bannerHightPlus
        else changeHightDp = screenHeightDp- bannerHightMin - bannerHightPlus
    }
//----------------------------------
    changeDp = 0.dp
//=========================
    scheduleViewModel.emptySelection()
//=========================

        FlowColumn(
            Modifier
                .padding(0.dp, changeDp + 0.dp, 0.dp, 0.dp)        // добавлен для баннера
                .verticalScroll(rememberScrollState())

        ) {

            SelectableCalendar(
                calendarState = state,
                dayContent = { dayState ->
                    Sch01RecipeDay(
                        state = dayState,
                        nonWorkingDaysViewState,
                    )
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                ""+ String.format("%4d", (getWorkingDays01Month (
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue,nonWorkingDaysViewState )))
                        + " "+ stringResource(R.string.schedule01_MonthWorkDays)
                        + String.format("%4d", (getWorkingHours01Month(state.monthState.currentMonth.year,
                    state.monthState.currentMonth.monthValue,nonWorkingDaysViewState )) )
                        + " "+ stringResource(R.string.schedule01_MonthWorkHours),
                fontSize = 15.sp.nonScaledSp,
                fontWeight = FontWeight.Bold
            )
        }
        if (state.selectionState.selection.isNotEmpty()) {
            scheduleViewModel.updateSelection(state.selectionState.selection )
            if (BuildConfig.DEBUG) {
                //-------------------------
                for (i in  state.selectionState.selection.lastIndex downTo 0 step 1) {
                    Log.d(
                        "Schedule01", "+++Schedule01Page: selected " +  state.selectionState.selection[i].dayOfMonth + "/"
                                +  state.selectionState.selection[i].monthValue + "/" +  state.selectionState.selection[i].year
                    )
                }
            }
        }
        if (nonWorkingDaysViewState.holiDays.count() > 0) {
            HoliDaysListScreen(nonWorkingDaysViewState)
        }
        if (nonWorkingDaysViewState.nonWorkingDays.count() > 0) {
            NonWorkingDaysListScreen(nonWorkingDaysViewState)
        }
}
/**
 * Custom implementation of DayContent, which shows a dot
 * if there is an recipe planned for this day.
 * Пользовательская реализация DayContent,
 * которая показывает точку, если на этот день запланирован рецепт.
 */
@Composable
fun Sch01RecipeDay(
    state: DayState<DynamicSelectionState>,
    nonWorkingDaysViewState : NonWorkingDaysViewState,
    modifier: Modifier = Modifier,
) {
  val date = state.date
  val selectionState = state.selectionState
  var colorsCard : CardColors

  val isSelected = selectionState.isDateSelected(date)

  colorsCard = CardDefaults.cardColors()

if (state.isCurrentDay)
      colorsCard = CardDefaults.cardColors(
            containerColor = Color.Green, //Card background color

            contentColor = Color.White  //Card content color,e.g.text
        )
if (isSelected)
        colorsCard = CardDefaults.cardColors(
            containerColor = Color.Cyan, //Card background color
        )

      Card(
          onClick = {selectionState.onDateSelected(date)},
          modifier = modifier
                .aspectRatio(1f)
                .padding(2.dp),
                  enabled = true,
          border = when ( getDayNonWorking (state,nonWorkingDaysViewState )){
                1 -> BorderStroke(3.dp, MaterialTheme.colorScheme.error) // праздник СЕГОДНЯ?
                2 -> BorderStroke(1.dp, MaterialTheme.colorScheme.secondary) // нерабочий СЕГОДНЯ?
                3 -> null //рабочая СБ
                4 -> BorderStroke(3.dp, MaterialTheme.colorScheme.primary) // // СБ или ВС выходной СЕГОДНЯ
                5 -> BorderStroke(1.dp, MaterialTheme.colorScheme.error)   // праздник не сегодня?
                6 -> BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)  // нерабочий  не сегодня?
                7 -> BorderStroke(1.dp, MaterialTheme.colorScheme.error)//сб вс и нерабочий не сегодня
                8 -> null // Рабочая СБ
                9 -> null // другой месяц
                else -> {null}
                },
          colors = colorsCard
   ) {
    Column(
        modifier = Modifier
            .align(CenterHorizontally)
            //====================================================
            // фиксация нажатия экрана для сдвига паказа рекламы
            .pointerInput(Unit) {
                /*
              detectTapAndPressUnconsumed(onTap = {
                  Log.d(YANDEX_MOBILE_ADS_TAG, "Schedule01 Interstitial:select date TAP")
                  yaAdsInterstutialTimerOff()  //реклама через 180 cек  durationNoPushTastaturAds
              })

               */
            }
            //--------------------------------------------------
            .clickable(true) {selectionState.onDateSelected(date)},
        horizontalAlignment = CenterHorizontally,
    ) {
        Text(
//                modifier = Modifier.background(androidx.compose.ui.graphics.Color.Red, CircleShape),
            fontSize = 20.sp.nonScaledSp,       //15.sp
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = date.dayOfMonth.toString(),
            style = MaterialTheme.typography.bodyLarge,
            color = when(nonWorkingDaysViewState.holiDaysOnlyDateYear.contains(date.toString())
                    || (date.dayOfWeek.value == 7)){
                        true -> MaterialTheme.colorScheme.error
                        else  -> MaterialTheme.colorScheme.primary
                    }
        )
         when ( getDayNonWorking (state,nonWorkingDaysViewState )) {
         //   1 -> Text(text = "",) // праздник СЕГОДНЯ?
         //   2 -> Text(text = "",) // нерабочий СЕГОДНЯ?
            3,8,9-> {       //рабочая СБ //рабочая СБ , //рабочая день
                Text(
                    text =
                        //String.format("%2d",(getShift01(date)).toInt())
                        //-------------------------holiDaysOnlyDateYear.contains(date.toString())
                        when (nonWorkingDaysViewState.holiDaysOnlyDateYear.contains( date.plusDays(1).toString())) {
                            true -> {
                                String.format("%2d",7)
                            }
                            else -> {String.format("%2d",8)}
                        },
                    //-----------------------------------------------------
                    fontSize = 15.sp.nonScaledSp,
                    style = MaterialTheme.typography.bodyMedium,
                    )
                }
         //   4 -> Text(text = "",)  // // СБ или ВС выходной СЕГОДНЯ
         //   5 -> Text(text = "",)    // праздник не сегодня?
         //   6 -> Text(text = "",)   // нерабочий  не сегодня?
         //   7 -> Text(text = "",) //сб вс и нерабочий не сегодня
         //   10 -> Text(text = "",) //другой месяц

            else -> Text(text = "")
        }
    }
  }
}
//====================================================================
// расчет основного времени до выбранной даты
//==============================================
@Composable
fun getDayNonWorking (state: DayState<DynamicSelectionState>,
                      nonWorkingDaysViewState : NonWorkingDaysViewState):Int {
    val date = state.date
    var nonWork: Int =0
     //-------------------------
    when  (state.isCurrentDay)  {   // праздник?
        true ->                 //LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            when (nonWorkingDaysViewState.holiDaysOnlyDateYear.contains(date.toString())){
                true -> nonWork =1
                else -> {               // нерабочий ?
                    when (nonWorkingDaysViewState.nonWorkingDaysOnlyDateYear.contains(date.toString())) {
                        true -> nonWork =2
                        else -> {
                            when (nonWorkingDaysViewState.nonWorkingDaysOnlyWorkDateYear.contains(date.toString())){
                                true -> {
                                    nonWork = 3     // рабочая суббота
                                }
                                else -> {
                                    when (date.dayOfWeek.value == 6 || date.dayOfWeek.value == 7) {
                                        true -> nonWork = 4    // СБ или ВС выходной
                                        else -> {
                                            nonWork = 9        // обычный день Сегодня
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        else -> {
            when (nonWorkingDaysViewState.holiDaysOnlyDateYear.contains(date.toString())){
                    true -> nonWork =5
                else -> {                       // нерабочий ?
                    when (nonWorkingDaysViewState.nonWorkingDaysOnlyDateYear.contains(date.toString())) {
                        true -> nonWork =6
                        else -> {               // СБ ВС и нерабочий ?
                            when (nonWorkingDaysViewState.nonWorkingDaysOnlyWorkDateYear.contains(date.toString())){
                                true -> nonWork =8     // рабочая суббота
                                else -> {
                                    when (date.dayOfWeek.value == 6 || date.dayOfWeek.value == 7) {
                                        true -> nonWork = 7    // СБ или ВС выходной
                                        else -> {
                                            nonWork = 9        // обычный день месяца
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return nonWork
}
//====================================================================
//====================================================================
// выбор рабочих дней и сокращенного дня
//==============================================
@Composable
fun getWorkingDay (year: Int, month: Int,day: Int,
                      nonWorkingDaysViewState : NonWorkingDaysViewState):Int {
    var nonWork: Int =0
    val date: LocalDate= LocalDate.of(year,month,day)
    //-------------------------
    when (nonWorkingDaysViewState.holiDaysOnlyDateYear.contains(date.toString())){
        true -> nonWork =1
        else -> {               // нерабочий ?
            when (nonWorkingDaysViewState.nonWorkingDaysOnlyDateYear.contains(date.toString())) {
                true -> nonWork =2
                else -> {
                    when (nonWorkingDaysViewState.nonWorkingDaysOnlyWorkDateYear.contains(date.toString())){
                        true -> {
                            nonWork = 3     // рабочая суббота
                        }
                        else -> {
                            when (date.dayOfWeek.value == 6 || date.dayOfWeek.value == 7) {
                                true -> nonWork = 4    // СБ или ВС выходной
                                else -> {
                                    nonWork = 5        // обычный рабочий день

                                }
                            }
                        }
                    }
                }
            }
        }
    }
    when (nonWorkingDaysViewState.holiDaysOnlyDateYear.contains(
        date.plusDays(1).toString())&&(nonWork==3 || nonWork==5))
    {
        true -> {
                nonWork = 7 // часовой рабочий день
        }
        else -> null
    }
    return nonWork
}
//====================================================================
/**
 * Enables for changing current selection mode.
 */
// расчет основного рабочего времени по дате по номеру бригады
//==============================================

fun getShift01 (dateCalc: LocalDate):Double
{
  val shift : Int
  shift=dateCalc.dayOfWeek.value

  when (shift) {
         1 -> return 8.0
        2 -> return 8.0
        3 -> return 8.0
        4 -> return 8.0
        5 -> return 8.0
        6 -> return 0.0
        7 -> return 0.0
        else -> return 099.0
      }
}
//====================================================================
// расчет основного времени до выбранной даты
//==============================================
@Composable
fun getWorkingDays01Month (year: Int, month: Int,
                     nonWorkingDaysViewState : NonWorkingDaysViewState):Int {
    var monthW: Int=month+1
    var yearW: Int=year
    if (monthW >12) {
        monthW=1
        yearW=yearW+1
    }
//  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1).minusDays(1)
    var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1)

    var summ: Int=0
    for (i in dateforCalc.minusDays(1).dayOfMonth downTo 1 step 1) {
        when ( getWorkingDay (year,month,dateforCalc.minusDays(i.toLong()).dayOfMonth,nonWorkingDaysViewState )) {
            3,5,7-> {       //рабочая СБ //рабочая СБ , //рабочая день
                when (nonWorkingDaysViewState.holiDaysOnlyDateYear.contains(
                    dateforCalc.minusDays(i.toLong()).plusDays(1).toString())) {
                    true -> {
                        summ +=1
                    }
                    else -> {
                        summ +=1
                    }
                }
            }
            //else -> null
        }
    }
    return summ
}
//====================================================================
// расчет основного времени до выбранной даты
//==============================================
@Composable
fun getWorkingHours01Month (year: Int, month: Int,
                           nonWorkingDaysViewState : NonWorkingDaysViewState):Int {
    var monthW: Int=month+1
    var yearW: Int=year
    if (monthW >12) {
        monthW=1
        yearW=yearW+1
    }
    var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1)

    var summ: Int=0
    for (i in dateforCalc.minusDays(1).dayOfMonth downTo 1 step 1) {
        when ( getWorkingDay (year,month,dateforCalc.minusDays(i.toLong()).dayOfMonth,nonWorkingDaysViewState )) {
            3,5,7-> {       //рабочая СБ //рабочая СБ , //рабочая день
                when (nonWorkingDaysViewState.holiDaysOnlyDateYear.contains(
                    dateforCalc.minusDays(i.toLong()).plusDays(1).toString())) {
                    true -> {
                        summ +=7
                    }
                    else -> {
                        summ +=8
                    }
                }
            }
            //else -> null
        }
    }
    return summ
}
