package com.childmathematics.android.shiftschedule.ui.schedules.schedule500


//import com.childmathematics.android.basement.lib.ads.util.detectTapAndPressUnconsumed
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import androidx.lifecycle.ViewModel
import com.childmathematics.android.basement.lib.composecalendar.SelectableCalendar
import com.childmathematics.android.basement.lib.composecalendar.day.DayState
import com.childmathematics.android.basement.lib.composecalendar.rememberSelectableCalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionMode.Period
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.ui.ScheduleViewModel
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

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalCoroutinesApi
@Composable
fun Schedule500Page(
                    scheduleViewModel: ScheduleViewModel ,
) {
//===========================================================================

    var changeDp: Dp

    val state = rememberSelectableCalendarState(
//        onSelectionChanged = viewModel::onSelectionChanged, //SelectionMode
        initialSelectionMode = Period,
    )


//------------------
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
//===========================================
    Box(
//        contentAlignment = Alignment.TopStart,
        modifier = Modifier
//            .background(Color.LightGray)
//            .padding(0.dp, changeDp, 0.dp, 0.dp)
//                .size(300.dp, 250.dp)               //(LocalConfiguration.current.screenHeightDp-50).dp
            .size(
                (LocalConfiguration.current.screenWidthDp).dp,
                changeHightDp.dp
            )
            .verticalScroll(rememberScrollState(changeHightDp))
//            .horizontalScroll(rememberScrollState())

    ) {

        //====================================================
        Column(
            Modifier
                .padding(0.dp, changeDp + 0.dp, 0.dp, 0.dp)        // добавлен для баннера
//                .padding(0.dp, changeDp + 50.dp, 0.dp, 0.dp)        // добавлен для баннера
 //               .verticalScroll(rememberScrollState())

        ) {
            SelectableCalendar(
                calendarState = state,
                dayContent = { dayState ->
                    Sch500RecipeDay(
                        state = dayState,
                        //plannedRecipe = recipes.firstOrNull { it.date == dayState.date },
                    )
                }
            )

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "\tВСЕГО за месяц:\n"
                        + "\tБригада 1:"
                    + String.format(
                    "%4d", (getShift500Month(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue,
                        1
                    )).toInt()
                )

                        + " ("
                    + String.format(
                    "%3d", (getShift500NightMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue, 1
                    )).toInt()
                )
                        + ") час",
                fontSize = 14.sp,
            )
            Text(
                text =
                "\tБригада 2:"
                    + String.format(
                    "%4d", (getShift500Month(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue,
                        2
                    )).toInt()
                )
                        + " ("
                    + String.format(
                    "%3d", (getShift500NightMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue, 2
                    )).toInt()
                )
                        + ") час",
                fontSize = 14.sp,
            )
            Text(
                text =
                "\tБригада 3:"
                    + String.format(
                    "%4d", (getShift500Month(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue,
                        3
                    )).toInt()
                )
                        + " ("
                    + String.format(
                    "%3d", (getShift500NightMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue, 3
                    )).toInt()
                )
                        + ") час",
                fontSize = 14.sp,
            )
            Text(
                text =
                "\tБригада 4:"
                    + String.format(
                    "%4d", (getShift500Month(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue,
                        4
                    )).toInt()
                )
                        + " ("
                    + String.format(
                    "%3d", (getShift500NightMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue, 4
                    )).toInt()
                )
                        + ") час",
                fontSize = 14.sp,
            )
            //========================================================================
            Text(
                text = "\n\tПояснение:\tв скобках - ночные часы",
                fontSize = 14.sp,
            )
            Text(
                text = "-------------",
                fontSize = 4.sp,
            )

//            Spacer(modifier = Modifier.height(5.dp))
        }


        if (state.selectionState.selection.isNotEmpty()) {
            scheduleViewModel.updateSelection(state.selectionState.selection )

            if (BuildConfig.DEBUG) {
                //-------------------------
                for (i in  state.selectionState.selection.lastIndex downTo 0 step 1) {
                    Log.d(
                        "Schedule500", "+++Schedule500Page: selected "
                                +  state.selectionState.selection[i].dayOfMonth + "/"
                                +  state.selectionState.selection[i].monthValue + "/"
                                +  state.selectionState.selection[i].year
                    )
                }
            }
        }
    }
}
/**
 * Custom implementation of DayContent, which shows a dot
 * if there is an recipe planned for this day.
 * Пользовательская реализация DayContent,
 * которая показывает точку, если на этот день запланирован рецепт.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Sch500RecipeDay(
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
      .aspectRatio(0.6f)
      .padding(2.dp),
      enabled = true,
//    elevation = if (state.isFromCurrentMonth) 4.dp else 0.dp,
    border = if (state.isCurrentDay) BorderStroke(3.dp, MaterialTheme.colorScheme.primary) else null,
    /*
      colors = if (isSelected) MaterialTheme.colorScheme.secondary
            else contentColorFor( backgroundColor = MaterialTheme.colorScheme.surface
            )

     */
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
                  Log.d(YANDEX_MOBILE_ADS_TAG, "Schedule500  Interstitial: TAP3")
                  yaAdsInterstutialTimerOff()  //реклама через 180 cек  durationNoPushTastaturAds
              })

               */
          }
          //--------------------------------------------------
          .clickable {
        selectionState.onDateSelected(date)
      },
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        fontSize = 20.sp,
        fontWeight= FontWeight.Bold,
//        Style= MaterialTheme.typography.subtitle2   ,


//        fontStyle = FONT_WEIGHT_BOLD,
//        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        text = date.dayOfMonth.toString(),
        style = MaterialTheme.typography.bodyLarge,

        )
        Text(
//          text = plannedRecipe.price.toString(),
          text = String.format("%2d",(getShift500(date,1)).toInt())
                  +" / "
                  +String.format("%2d",(getShift500(date,2)).toInt()),
          fontSize = 12.sp,
//            fontWeight= FontWeight.Bold,
          style = MaterialTheme.typography.bodyMedium,
        )
        Text(
//          text = plannedRecipe.price.toString(),
          text = String.format("%2d",(getShift500(date,3)).toInt())
                  +" / "
                  +String.format("%2d",(getShift500(date,4)).toInt()),
          fontSize = 12.sp,
//            fontWeight= FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
  }
}


data class Sch500PlannedRecipe(
  val date: LocalDate,
  val price: Double,
)

/**
 * ViewModel exposing list of our recipes
 */
class Sch500RecipeViewModel : ViewModel() {


  private val selectionFlow = MutableStateFlow(emptyList<LocalDate>())
    /*
  val recipesFlow = MutableStateFlow(
    listOf(
//      PlannedRecipe(LocalDate.now().plusDays(1), getLong(LocalDate(2022,1,31))),
      Sch500PlannedRecipe(LocalDate.now().plusDays(1), 20.0),
      Sch500PlannedRecipe(LocalDate.now().plusDays(3), 20.0),
      Sch500PlannedRecipe(LocalDate.now().plusDays(5), 10.0),
      Sch500PlannedRecipe(LocalDate.now().plusDays(-2), 25.0),
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
/*
@ExperimentalCoroutinesApi
@Preview
@Composable
fun Schedule500SamplePreview( ) {
  MaterialTheme {
    Schedule500Sample(RoutesSchedule500.SCHEDULE500SELNULL)
//    Schedule500Sample()
  }
}
*/
//====================================================================
// расчет основного рабочего времени по дате по номеру бригады
//==============================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getShift500 (dateforCalc: LocalDate,nBrig: Int):Double
{
  val shift : Int
  shift=(dateforCalc.toEpochDay() % 4).toInt()

  when (shift) {
    0 -> {
      when (nBrig){
        1 -> return 0.0
        2 -> return 8.0
        3 -> return 4.0
        4 -> return 12.0
        else -> return 0.0
      }
    }
    1 -> {
      when (nBrig){
        1 -> return 12.0
        2 -> return 0.0
        3 -> return 8.0
        4 -> return 4.0
        else -> return 0.0
      }
    }
    2 -> {
      when (nBrig){
        1 -> return 4.0
        2 -> return 12.0
        3 -> return 0.0
        4 -> return 8.0
        else -> return 0.0
      }
    }
    3 -> {
      when (nBrig){
        1 -> return 8.0
        2 -> return 4.0
        3 -> return 12.0
        4 -> return 0.0
        else -> return 0.0
      }
    }
  }
    if (nBrig > 4 || nBrig<1) {return -1999.0}
  return -2999.0
}
//====================================================================
// расчет ночных по дате по номеру бригады
//==============================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getShift500Night (dateforCalc: LocalDate,nBrig: Int):Double
{
  val shift : Int
  shift=(dateforCalc.toEpochDay() % 4).toInt()

  when (shift) {
    0 -> {
      when (nBrig){
        1 -> return 0.0
        2 -> return 6.0
        3 -> return 2.0
        4 -> return 0.0
        else -> return 0.0
      }
    }
    1 -> {
      when (nBrig){
        1 -> return 0.0
        2 -> return 0.0
        3 -> return 6.0
        4 -> return 2.0
        else -> return 0.0
      }
    }
    2 -> {
      when (nBrig){
        1 -> return 2.0
        2 -> return 0.0
        3 -> return 0.0
        4 -> return 6.0
        else -> return 0.0
      }
    }
    3 -> {
      when (nBrig){
        1 -> return 6.0
        2 -> return 2.0
        3 -> return 0.0
        4 -> return 0.0
        else -> return 0.0
      }
    }
  }
  if (nBrig > 4 || nBrig<1) {return -1999.0}
  return -2999.0
}
//====================================================================
// расчет основного рабочего времени по дате по номеру бригады
//==============================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getShift500Text (dateforCalc: LocalDate,nBrig: Int):String
{
    val shift : Int
    shift=(dateforCalc.toEpochDay() % 4).toInt()

    when (shift) {
        0 -> {
            when (nBrig){
                1 -> return "выходной"
                2 -> return "с ночи"
                3 -> return "в ночь"
                4 -> return "в день"
                else -> return "Error"
            }
        }
        1 -> {
            when (nBrig){
                1 -> return "в день"
                2 -> return "выходной"
                3 -> return "с ночи"
                4 -> return "в ночь"
                else -> return "Error"
            }
        }
        2 -> {
            when (nBrig){
                1 -> return "в ночь"
                2 -> return "в день"
                3 -> return "выходной"
                4 -> return "с ночи"
                else -> return "Error"
            }
        }
        3 -> {
            when (nBrig){
                1 -> return "с ночи"
                2 -> return "в ночь"
                3 -> return "в день"
                4 -> return "выходной"
                else -> return "Error"
            }
        }
    }
    if (nBrig > 4 || nBrig<1) {return "Error"}
    return "Error"
}
//====================================================================
// расчет ночных до выбранной даты по номеру бригады
//==============================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getShift500NightSelect(selection: List<LocalDate>,nBrig: Int):Double {
  var summ: Double =getShift500Night(selection[selection.lastIndex] ,nBrig)
  for (i in selection.lastIndex downTo 0 step 1) {
    summ+=getShift500Night(selection[selection.lastIndex-i] ,nBrig)
  }
return summ
}
//====================================================================
// расчет основного времени до выбранной даты по номеру бригады
//==============================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getShift500Select (selection: List<LocalDate>,nBrig: Int):Double {
  var summ: Double =getShift500(selection[selection.lastIndex] ,nBrig)
  for (i in selection.lastIndex downTo 0 step 1) {
    summ+=getShift500(selection[selection.lastIndex-i] ,nBrig)
  }
  return summ
}//====================================================================
//====================================================================
// расчет ночных до выбранной даты по номеру бригады
//==============================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getShift500NightMonth (year: Int,month: Int,nBrig: Int):Double {
  var monthW: Int=month+1
  var yearW: Int=year
  if (monthW >12) {
    monthW=1
    yearW=yearW+1
  }
  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1)
  var summ: Double =0.0
  for (i in dateforCalc.minusDays(1).dayOfMonth downTo 1 step 1) {
    summ+=getShift500Night(dateforCalc.minusDays(i.toLong()) ,nBrig)
  }
  return summ
}
//====================================================================
// расчет основного времени до выбранной даты по номеру бригады
//==============================================

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getShift500Month (year: Int,month: Int,nBrig: Int):Double {
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
        summ+=getShift500(dateforCalc.minusDays(i.toLong()) ,nBrig)
    }
    return summ
}
//====================================================================
// расчет основного времени до выбранной даты по номеру бригады с начала месяца
//==============================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getShift500MonthDate (datecalc: LocalDate,nBrig: Int):Double {
//  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1).minusDays(1)
    var dateforCalc: LocalDate= datecalc

    var summ: Double =getShift500(dateforCalc, nBrig)
    for (i in dateforCalc.dayOfMonth-1 downTo 1 step 1) {
            summ += getShift500(dateforCalc.minusDays(i.toLong()), nBrig)
    }
    return summ
}
//====================================================================
// расчет основного времени до выбранной даты по номеру бригады с начала месяца
//==============================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getShift500MonthDateNight (datecalc: LocalDate,nBrig: Int):Double {
//  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1).minusDays(1)
    var dateforCalc: LocalDate= datecalc

    var summ: Double =getShift500Night(dateforCalc ,nBrig)
    for (i in dateforCalc.dayOfMonth-1 downTo 1 step 1) {
        summ+=getShift500Night(dateforCalc.minusDays(i.toLong()) ,nBrig)
    }
    return summ
}
//====================================================================
// расчет основного времени между выбранной даты по номеру бригады с начала месяца
//==============================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getShift500Date1Date2 (date1: LocalDate,date2: LocalDate,nBrig: Int):Double {
//  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1).minusDays(1)
    var dateforCalc: LocalDate= date2

    var summ: Double =getShift500(dateforCalc, nBrig)
    for (i in dateforCalc.toEpochDay()-date1.toEpochDay() downTo 1 step 1) {
        summ += getShift500(dateforCalc.minusDays(i.toLong()), nBrig)
    }
    return summ
}
//====================================================================
// расчет основного времени между выбранной даты по номеру бригады с начала месяца
//==============================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getShift500Date1Date2Night (date1: LocalDate,date2: LocalDate,nBrig: Int):Double {
//  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1).minusDays(1)
    var dateforCalc: LocalDate= date2

    var summ: Double = getShift500Night(dateforCalc, nBrig)
    for (i in dateforCalc.toEpochDay()-date1.toEpochDay() downTo 1 step 1) {
        summ += getShift500Night(dateforCalc.minusDays(i.toLong()), nBrig)
    }
    return summ
}

