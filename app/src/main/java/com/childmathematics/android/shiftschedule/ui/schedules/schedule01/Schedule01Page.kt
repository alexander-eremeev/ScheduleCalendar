package com.childmathematics.android.shiftschedule.ui.schedules.schedule01

//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import com.childmathematics.android.basement.lib.ads.util.detectTapAndPressUnconsumed
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.childmathematics.android.basement.lib.composecalendar.CalendarState
import com.childmathematics.android.basement.lib.composecalendar.SelectableCalendar
import com.childmathematics.android.basement.lib.composecalendar.day.DayState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.EmptySelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionMode
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.ui.AppViewModelProvider
import com.childmathematics.android.shiftschedule.util.bannerHightMin
import com.childmathematics.android.shiftschedule.util.bannerHightPlus
import com.childmathematics.android.shiftschedule.util.bannerHightWithVideoMin
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate


/**
 * In this sample, calendar composable is wired with an ViewModel. It's purpose is to show how to use
 * the composable in real world use-case, by an example implementation of a calendar
 * which can display planned recipes along with their prices
 */


@ExperimentalCoroutinesApi
@Composable
fun Schedule01Page(currentDialog: Boolean, state: CalendarState<DynamicSelectionState>,
                   schedule01PageViewModel: Schedule01PageViewModel = viewModel()
//                           viewModel: Schedule01PageViewModel = viewModel(factory = AppViewModelProvider.Factory)
                           //gameViewModel: GameViewModel = viewModel()
)
//fun Schedule01Page(currentDialog: Boolean)
{
//===========================================================================
//    val Schedule01PageViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val schedule01PageUiState by schedule01PageViewModel.schedule01PageUiState.collectAsState()
    //===========================================================================

//    val viewModel = remember { Sch01RecipeViewModel() }

    var showDialog by rememberSaveable { mutableStateOf(false) }
    var changeDp: Dp
    /*
    schedule01PageUiState.state = rememberSelectableCalendarState(
//.        onSelectionChanged = viewModel::onSelectionChanged, //SelectionMode
//        confirmSelectionChange = viewModel::onSelectionChanged, //SelectionMode

        initialSelectionMode = Period,
    )

     */

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

    Box(
//        contentAlignment = Alignment.TopStart,
        modifier = Modifier
//            .background(Color.LightGray)
//            .padding(0.dp, changeDp, 0.dp, 0.dp)
//                .size(300.dp, 250.dp)               //(LocalConfiguration.current.screenHeightDp-50).dp
            .size(
                (LocalConfiguration.current.screenWidthDp).dp,
                changeHightDp.dp)
            .verticalScroll(rememberScrollState(changeHightDp))
//            .horizontalScroll(rememberScrollState())
    ) {

        Column(
            Modifier
                .padding(0.dp, changeDp + 0.dp, 0.dp, 0.dp)        // добавлен для баннера
//                .padding(0.dp, changeDp + 50.dp, 0.dp, 0.dp)        // добавлен для баннера
//                .verticalScroll(rememberScrollState())

        ) {
            SelectableCalendar(
                calendarState = state,
                dayContent = { dayState ->
                    Sch01RecipeDay(
                        state = dayState,
                        //plannedRecipe = recipes.firstOrNull { it.date == dayState.date },
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                        ""+ String.format("%4d", (getShift01WorkDayMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue
                    ))
                )
//                        + "\tраб.дн.\t" //schedule01_MonthWorkDays
                        + stringResource(R.string.schedule01_MonthWorkDays)
                        + String.format(
                    "%4d", (getShift01Month(
                        state.monthState.currentMonth.year, state.monthState.currentMonth.monthValue
                    )).toInt()
                )
//                        + "\tчас.\n",  //schedule01_MonthWorkHours
                          + stringResource(R.string.schedule01_MonthWorkHours),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,

                )
            //========================================================================
            Spacer(modifier = Modifier.height(20.dp))
            //--------------------------------------

        }
//        schedule01PageUiState.vmselection= state.selectionState.selection
/*
   if (BuildConfig.DEBUG) {
     //-------------------------
     for (i in  state.selectionState.selection.lastIndex downTo 0 step 1) {
         Log.d(
           "Schedule500", "+++++SCHEDULE500SELSINGLE: " +  state.selectionState.selection[i].dayOfMonth + "/"
                   +  state.selectionState.selection[i].monthValue + "/" +  state.selectionState.selection[i].year
         )
     }
       Log.d(
         "Schedule500", "+++++SCHEDULE500SELSINGLE:======="
       )
   }
*/

            if (state.selectionState.selection.isNotEmpty()) {
//                schedule01PageUiState.updateSelection(state.selectionState.selection )
                schedule01PageViewModel.updateSelection(state.selectionState.selection )
                if (BuildConfig.DEBUG) {
                    //-------------------------
                    for (i in  state.selectionState.selection.lastIndex downTo 0 step 1) {
                        Log.d(
                            "Schedule01", "+++Schedule01Page: selected " +  state.selectionState.selection[i].dayOfMonth + "/"
                                    +  state.selectionState.selection[i].monthValue + "/" +  state.selectionState.selection[i].year
                        )
                    }
                }
/*
                Toast.makeText(

                    LocalContext.current,
                    "Есть выделенные даты для расчета!! ",
                    Toast.LENGTH_LONG
                ).show()

 */

            }

            if (state.selectionState.selection.size == 0) {
                if (BuildConfig.DEBUG) {
                        Log.d(
                    "Schedule01", "+++Schedule01Page: "+stringResource(R.string.schedule01_NoSelectedDays))
                    }

            }

     }
//--------------------------
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
  //plannedRecipe: Sch500PlannedRecipe?,
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
    border = if (state.isCurrentDay && (date.dayOfWeek.value==6 || date.dayOfWeek.value==7))
                        BorderStroke(3.dp, MaterialTheme.colorScheme.error)
            else if(state.isCurrentDay){ BorderStroke(3.dp, MaterialTheme.colorScheme.primary)}
            else if (state.isFromCurrentMonth && (date.dayOfWeek.value==6 || date.dayOfWeek.value==7))
                            BorderStroke(1.dp, MaterialTheme.colorScheme.error)
            else null,
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
          .clickable (true){
                        selectionState.onDateSelected(date)
      }
        ,
      horizontalAlignment = CenterHorizontally,
    ) {
            Text(
//                modifier = Modifier.background(androidx.compose.ui.graphics.Color.Red, CircleShape),
                fontSize = 20.sp,       //15.sp
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                text = date.dayOfMonth.toString(),
          style = MaterialTheme.typography.bodyLarge,

            )
        if (date.dayOfWeek.value==6 || date.dayOfWeek.value==7) {
            Text(
                text = "",
            )
        }
            else {
            Text(
//          text = plannedRecipe.price.toString(),
                text = //String.format("%2d",(getShift01(date)).toInt())
//                  +" / "+
                String.format("%2d", (getShift01(date)).toInt()),
                fontSize = 15.sp,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
  }
}

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
fun getShift01 (dateforCalc: LocalDate):Double
{
  val shift : Int
  shift=dateforCalc.dayOfWeek.value

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
