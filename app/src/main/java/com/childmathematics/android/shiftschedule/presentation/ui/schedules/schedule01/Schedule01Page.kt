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

/**
 * In this sample, calendar composable is wired with an ViewModel. It's purpose is to show how to use
 * the composable in real world use-case, by an example implementation of a calendar
 * which can display planned recipes along with their prices
 */
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
        true -> null
        else -> {
            nonWorkingDaysViewModel.handleIntent(NonWorkingDaysViewIntent.LoadHoliDays(year,month))
            nonWorkingDaysViewModel.handleIntent(NonWorkingDaysViewIntent.LoadNonWorkingDays(year,month))
        }
    }
    when (nonWorkingDaysViewState.year == year){
        true -> null
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
    var sumWorkDaysInMonth: Int=0
    var sumWorkHoursInMonth: Int=0

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
//                .weight(.70f)
        //                .padding(0.dp, changeDp + 50.dp, 0.dp, 0.dp)        // добавлен для баннера
                .verticalScroll(rememberScrollState())

        ) {

            SelectableCalendar(
//                modifier = Modifier .weight(.70f),
                calendarState = state,
                dayContent = { dayState ->
                    Sch01RecipeDay(
                        state = dayState,
                        nonWorkingDaysViewState,
                        //plannedRecipe = recipes.firstOrNull { it.date == dayState.date },
                    )
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                ""+ String.format("%4d", (getShift01WorkDayMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue)))
                        + " "+ stringResource(R.string.schedule01_MonthWorkDays)
                        + String.format("%4d", (getShift01Month(state.monthState.currentMonth.year,
                            state.monthState.currentMonth.monthValue )).toInt() )
                        + " "+ stringResource(R.string.schedule01_MonthWorkHours),
                fontSize = 15.sp.nonScaledSp,
                fontWeight = FontWeight.Bold
            )
            //========================================================================
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
//------------------------------------------------
            if (nonWorkingDaysViewState.holiDays.count() > 0) {
//                HoliDaysHeader()
                HoliDaysListScreen(nonWorkingDaysViewState)
            }
            if (nonWorkingDaysViewState.nonWorkingDays.count() > 0) {
//                NonWorkingDaysHeader()
                NonWorkingDaysListScreen(nonWorkingDaysViewState)
            }
//------------------------------------------------
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
//    sumWorkDaysInMonth: Int,
//    sumWorkHoursInMonth: Int,
    modifier: Modifier = Modifier,
) {
  val date = state.date
  val selectionState = state.selectionState
  var colorsCard : CardColors

  val isSelected = selectionState.isDateSelected(date)

  colorsCard = CardDefaults.cardColors()
    //-----------------------------------------------------------------------------
    /*
             when ( getDayNonWorking (state,nonWorkingDaysViewState )) {
         //   1 -> Text(text = "",) // праздник СЕГОДНЯ?
         //   2 -> Text(text = "",) // нерабочий СЕГОДНЯ?
            3,8,9-> {       //рабочая СБ //рабочая СБ , //рабочая день
                        when (nonWorkingDaysViewState.holiDaysOnlyDateYear.contains( date.plusDays(1).toString())) {
                            true -> {
                                sumWorkDaysInMonth =1
                                sumWorkHoursInMonth +=7
                            }
                            else -> {
                                sumWorkDaysInMonth +=1
                                sumWorkHoursInMonth +=8
                            }
                        }
                }
         //   4 -> Text(text = "",)  // // СБ или ВС выходной СЕГОДНЯ
         //   5 -> Text(text = "",)    // праздник не сегодня?
         //   6 -> Text(text = "",)   // нерабочий  не сегодня?
         //   7 -> Text(text = "",) //сб вс и нерабочий не сегодня
         //   10 -> Text(text = "",) //другой месяц

            else -> null
        }

     */
     //-----------------------------------------------------------------------

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
            .clickable(true) {
                selectionState.onDateSelected(date)
            },
        horizontalAlignment = CenterHorizontally,
    ) {
            Text(
//                modifier = Modifier.background(androidx.compose.ui.graphics.Color.Red, CircleShape),
                fontSize = 20.sp.nonScaledSp,       //15.sp
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                text = date.dayOfMonth.toString(),
          style = MaterialTheme.typography.bodyLarge,
//------------------------------------------------------------
                color = when(nonWorkingDaysViewState.holiDaysOnlyDateYear.contains(date.toString())
                        || (date.dayOfWeek.value == 7)){
//                        && state.isFromCurrentMonth )|| (date.dayOfWeek.value == 7)){
                            true -> MaterialTheme.colorScheme.error
                            else  -> MaterialTheme.colorScheme.primary
                        }
//-----------------------------------------------------------
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
//    val dateFormatter: DateTimeFormatter? = DateTimeFormatter.ofPattern("yyyyMMdd")

    val date = state.date
    var nonWork: Int =0
     //-------------------------
    when  (state.isCurrentDay)  {   // праздник?
        true ->                 //LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            when (nonWorkingDaysViewState.holiDaysOnlyDateYear.contains(date.toString())){
//            when (nonWorkingDaysViewState.holiDaysOnlyDays.contains( date.dayOfMonth)){
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
//            when (state.isFromCurrentMonth){
//                true ->                             // праздник?
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
//                else -> {nonWork =10} // другой месяц
//            }
        }
    }
//---------------------------------
    return nonWork
}
//====================================================================

/**
 * Enables for changing current selection mode.
 */
@Composable
private fun SelectionControls(
    selectionState: DynamicSelectionState,
) {
  if (BuildConfig.DEBUG) {
    Log.d("Schedule01", "SelectionControls")
  }
  Text(
    text = "Calendar Selection Mode",
    style = MaterialTheme.typography.headlineSmall,
  )
  SelectionMode.values().forEach { selectionMode ->
    Row(modifier = Modifier.fillMaxWidth()) {
      RadioButton(
        selected = selectionState.selectionMode == selectionMode,
        onClick = { selectionState.selectionMode = selectionMode }
      )
      Text(text = selectionMode.name)
      Spacer(modifier = Modifier.height(4.dp))
    }
  }
}
data class Sch01PlannedRecipe(
  val date: LocalDate,
  val price: Double,
)
/**
 * ViewModel exposing list of our recipes
 */
/*
class Sch01RecipeViewModel : ViewModel() {
  private val selectionFlow = MutableStateFlow(emptyList<LocalDate>())
/*
  val recipesFlow = MutableStateFlow(
    listOf(
//      PlannedRecipe(LocalDate.now().plusDays(1), getLong(LocalDate(2022,1,31))),
      Sch01PlannedRecipe(LocalDate.now().plusDays(1), 20.0),
      Sch01PlannedRecipe(LocalDate.now().plusDays(3), 20.0),
      Sch01PlannedRecipe(LocalDate.now().plusDays(5), 10.0),
      Sch01PlannedRecipe(LocalDate.now().plusDays(-2), 25.0),
    )
  )
  val selectedRecipesPriceFlow = recipesFlow.combine(selectionFlow) { recipes, selection ->
    recipes.filter { it.date in selection }.sumOf { it.price }
  }
*/
//@Composable
fun onSelectionChanged(selection: List<LocalDate>) {
  for (i in selection.lastIndex downTo 0 step 1) {
    if (BuildConfig.DEBUG) {
//      Log.d(
//        "Schedule500", "onSelectionChanged: " + selection[i].dayOfMonth + "/"
//                + selection[i].monthValue + "/" + selection[i].year
//      )
    }
  }
//============================
  }
}

 */
//====================================================================
// расчет основного рабочего времени по дате по номеру бригады
//==============================================
//                                    state.date.plusDays(1)
//                                    state.date.dayOfYear
//                                    Date(2025,12,31)

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
fun getShift01Select (selection: List<LocalDate>):Double {
  var summ: Double =getShift01(selection[selection.lastIndex] )
  for (i in selection.lastIndex downTo 0 step 1) {
    summ+=getShift01(selection[selection.lastIndex-i] )
  }
  return summ
}
//====================================================================
// расчет рабочих дней до конца месяца
//==============================================
fun getShift01WorkDayMonth (year: Int, month: Int):Int {
  var monthW: Int=month+1
  var yearW: Int=year
  if (monthW >12) {
    monthW=1
    yearW=yearW+1
  }
  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1)
  var daysInMonth:Int =0
  for (i in dateforCalc.minusDays(1).dayOfMonth downTo 1 step 1) {
      if (getShift01(dateforCalc.minusDays(i.toLong())) >0.0)
          daysInMonth  +=1
  }
  return daysInMonth
}
//====================================================================
// расчет основного времени до выбранной даты
//==============================================
fun getShift01Month (year: Int, month: Int):Double {
    var monthW: Int=month+1
    var yearW: Int=year
    if (monthW >12) {
        monthW=1
        yearW=yearW+1
    }
//  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1).minusDays(1)
    var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1)

    var summ: Double =0.0
    for (i in dateforCalc.minusDays(1).dayOfMonth downTo 1 step 1) {
        summ+=getShift01(dateforCalc.minusDays(i.toLong()) )
    }
    return summ
}
//====================================================================
// расчет основного времени до выбранной даты по номеру бригады с начала месяца
//==============================================
fun getShift01MonthDateDays (datecalc: LocalDate):Int {
//  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1).minusDays(1)
    var dateforCalc: LocalDate= datecalc

    var summDays: Int
    if (getShift01(dateforCalc)>0.0) summDays  =1 else summDays  =0
    for (i in dateforCalc.dayOfMonth-1 downTo 1 step 1) {
        if (getShift01(dateforCalc.minusDays(i.toLong())) >0.0)
            summDays  +=1
    }
    return summDays
}
//====================================================================
// расчет основного времени до выбранной даты по номеру бригады с начала месяца
//==============================================
fun getShift01MonthDate (datecalc: LocalDate):Double {
//  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1).minusDays(1)
    var dateforCalc: LocalDate= datecalc

    var summ: Double =getShift01(dateforCalc)
    for (i in dateforCalc.dayOfMonth-1 downTo 1 step 1) {
            summ += getShift01(dateforCalc.minusDays(i.toLong()))
    }
    return summ
}
