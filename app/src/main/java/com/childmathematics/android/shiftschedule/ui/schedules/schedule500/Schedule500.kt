package com.childmathematics.android.shiftschedule.ui.schedules.schedule500


import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.ViewModel
import com.childmathematics.android.basement.lib.ads.yandex.YANDEX_MOBILE_ADS_TAG
//import com.childmathematics.android.basement.lib.ads.util.detectTapAndPressUnconsumed
import com.childmathematics.android.basement.lib.ads.yandex.mYaInterstitialAdOnOff
import com.childmathematics.android.basement.lib.ads.yandex.yaAdsInterstutialTimerOff
import com.childmathematics.android.basement.lib.composecalendar.SelectableCalendar
import com.childmathematics.android.basement.lib.composecalendar.day.DayState
import com.childmathematics.android.basement.lib.composecalendar.rememberSelectableCalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionMode.Period
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.util.DialogButtonOK
import com.childmathematics.android.shiftschedule.util.bannerHightMin
import com.childmathematics.android.shiftschedule.util.bannerHightPlus
import com.childmathematics.android.shiftschedule.util.bannerHightWithVideoMin
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.*

/**
 * In this sample, calendar composable is wired with an ViewModel. It's purpose is to show how to use
 * the composable in real world use-case, by an example implementation of a calendar
 * which can display planned recipes along with their prices
 */

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalCoroutinesApi
@Composable
fun Schedule500Page(currentDialog500: Boolean) {
//===========================================================================
    val viewModel = remember { Sch500RecipeViewModel() }
    var showDialog by rememberSaveable { mutableStateOf(false) }

    var changeDp: Dp
    val state = rememberSelectableCalendarState(
        onSelectionChanged = viewModel::onSelectionChanged, //SelectionMode
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

//------------------------------------------------

//-------------------------------------------------
        Spacer(
            modifier = Modifier.height(20.dp)

        )
        Column(
            Modifier
                .padding(0.dp, changeDp, 0.dp, 0.dp)        // добавлен для баннера
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "График непрерывный 12 часовой 4-х бригадный 2-х сменный ",
                style = MaterialTheme.typography.bodyLarge ,
//      color = MaterialTheme.colors.secondary,
                softWrap = true,
                fontStyle = Italic,
                maxLines = 2,
                textDecoration = TextDecoration.Underline,
//      modifier = Modifier
//           .align(CenterHorizontally),
            )
        }
        Spacer(modifier = Modifier.height(20.dp))  //====================================================
        Column(
            Modifier
                .padding(0.dp, changeDp + 50.dp, 0.dp, 0.dp)        // добавлен для баннера
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

/*
                        + String.format(
                    "%4d", (getShift500NightMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue, 1
                    )).toInt()
                )
 */
                        + " ("
                    + String.format(
                    "%3d", (getShift500NightMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue, 1
                    )).toInt()
                )
/*
                        + String.format(
                    "%4d", (getShift500Month(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue,
                        1
                    )).toInt()
                )

 */
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
/*
                        + String.format(
                    "%4d", (getShift500NightMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue, 2
                    )).toInt()
                )
 */
                        + " ("
                    + String.format(
                    "%3d", (getShift500NightMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue, 2
                    )).toInt()
                )
/*
                        + String.format(
                    "%4d", (getShift500Month(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue,
                        2
                    )).toInt()
                )
 */
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
/*
                        + String.format(
                    "%4d", (getShift500NightMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue, 3
                    )).toInt()
                )
 */
                        + " ("
                    + String.format(
                    "%3d", (getShift500NightMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue, 3
                    )).toInt()
                )
/*
                        + String.format(
                    "%4d", (getShift500Month(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue,
                        3
                    )).toInt()
                )
 */
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
/*
                        + String.format(
                    "%4d", (getShift500NightMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue, 4
                    )).toInt()
                )
 */
                        + " ("
                    + String.format(
                    "%3d", (getShift500NightMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue, 4
                    )).toInt()
                )
/*
                        + String.format(
                    "%4d", (getShift500Month(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue,
                        4
                    )).toInt()
                )
 */
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

        if (currentDialog500 != showDialog) {
            if (!state.selectionState.selection.isEmpty()) {
                DialogSchedule500(state.selectionState.selection) {
                    showDialog = !showDialog
                }
            }
            if (state.selectionState.selection.size == 0) {
//         Toast.makeText(LocalContext.current,"Нет выделенных дат для расчета!! ",Toast.LENGTH_SHORT).show()
                Toast.makeText(
                    LocalContext.current,
                    "Нет выделенных дат для расчета!! ",
                    Toast.LENGTH_LONG
                ).show()
                showDialog = !showDialog
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

  val isSelected = selectionState.isDateSelected(date)

  Card(
    modifier = modifier
      .aspectRatio(1f)
      .padding(2.dp),
//    elevation = if (state.isFromCurrentMonth) 4.dp else 0.dp,
    border = if (state.isCurrentDay) BorderStroke(1.dp, MaterialTheme.colorScheme.primary) else null,
    /*
      colors = if (isSelected) MaterialTheme.colorScheme.secondary
            else contentColorFor( backgroundColor = MaterialTheme.colorScheme.surface
            )

     */
  ) {
    Column(
      modifier = Modifier
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
        fontSize = 10.sp,
        fontWeight= FontWeight.Bold,
//        Style= MaterialTheme.typography.subtitle2   ,


//        fontStyle = FONT_WEIGHT_BOLD,
//        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        text = date.dayOfMonth.toString(),

        )
        Text(
//          text = plannedRecipe.price.toString(),
          text = String.format("%2d",(getShift500(date,1)).toInt())
                  +" / "
                  +String.format("%2d",(getShift500(date,2)).toInt()),
          fontSize = 9.sp,
//          style = MaterialTheme.typography.body2,
        )
        Text(
//          text = plannedRecipe.price.toString(),
          text = String.format("%2d",(getShift500(date,3)).toInt())
                  +" / "
                  +String.format("%2d",(getShift500(date,4)).toInt()),
          fontSize = 9.sp,
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
//====================================================================
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DialogSchedule500(selection: List<LocalDate>,onDismiss: () -> Unit) {


  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties()
  ) {
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
                +String.format("%3d",getShift500 (selection[0],1).toInt())
                +" ("
                +String.format("%1d",getShift500Night (selection[0],1).toInt())
                +") час"
//                +"\t"
                +String.format("%10s",getShift500Text (selection[0],1))
          ,
        fontSize = 15.sp,    )


      Text(
        text = "Бригада 2:"
                +String.format("%3d",getShift500 (selection[0],2).toInt())
                +" ("
                +String.format("%1d",getShift500Night (selection[0],2).toInt())
                +") час"
//                +"\t"
                +String.format("%10s",getShift500Text (selection[0],2))
          ,
         fontSize = 15.sp,
      )


      Text(
        text = "Бригада 3:"
                +String.format("%3d",getShift500 (selection[0],3).toInt())
                +" ("
                +String.format("%1d",getShift500Night (selection[0],3).toInt())
                +") час"
//                +"\t"
                +String.format("%10s",getShift500Text (selection[0],3))
          ,

        fontSize = 15.sp,
      )
        Text(
            text = "Бригада 4:"
                    +String.format("%3d",getShift500 (selection[0],4).toInt())
                    +" ("
                    +String.format("%1d",getShift500Night (selection[0],4).toInt())
                    +") час"
//                    +"\t"
                    +String.format("%10s",getShift500Text (selection[0],4))
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
                    +String.format("%5d",getShift500MonthDate (selection[0],1).toInt())
                    +" ("
                    +String.format("%5d",getShift500MonthDateNight (selection[0],1).toInt())
                    +") час"
            ,
            fontSize = 15.sp,    )


        Text(
            text = "\tБригада 2:"
                    +String.format("%5d",getShift500MonthDate (selection[0],2).toInt())
                    +" ("
                    +String.format("%5d",getShift500MonthDateNight (selection[0],2).toInt())
                    +") час"
            ,
            fontSize = 15.sp,
            //style = MaterialTheme.typography.h6,
        )


        Text(
            text = "\tБригада 3:"
                    +String.format("%5d",getShift500MonthDate(selection[0],3).toInt())
                    +" ("
                    +String.format("%5d",getShift500MonthDateNight (selection[0],3).toInt())
                    +") час"
            ,

            fontSize = 15.sp,
        )
        Text(                                   //getShift500MonthDate (datecalc: LocalDate,nBrig: Int):Double
            text = "\tБригада 4:"
                    +String.format("%5d",getShift500MonthDate (selection[0],4).toInt())
                    +" ("
                    +String.format("%5d",getShift500MonthDateNight (selection[0],4).toInt())
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
                      +String.format("%5d",getShift500Date1Date2 (selection[0],selection[selection.lastIndex],1).toInt())
                      +" ("
                      +String.format("%5d",getShift500Date1Date2Night (selection[0],selection[selection.lastIndex],1).toInt())
                      +") час"
              ,
              fontSize = 15.sp,    )


          Text(
              text = "\tБригада 2:"
//                +"\t/\tРабочих:\t"
                      +String.format("%5d",getShift500Date1Date2 (selection[0],selection[selection.lastIndex],2).toInt())
//                +"Ночных:\t"
                      +" ("
                      +String.format("%5d",getShift500Date1Date2Night (selection[0],selection[selection.lastIndex],2).toInt())
                      +") час"
              ,
              fontSize = 15.sp,
          )


          Text(
              text = "\tБригада 3:"
                      +String.format("%5d",getShift500Date1Date2 (selection[0],selection[selection.lastIndex],3).toInt())
                      +" ("
                      +String.format("%5d",getShift500Date1Date2Night (selection[0],selection[selection.lastIndex],3).toInt())
                      +") час"
              ,
              fontSize = 15.sp,
          )
          Text(                                   //getShift500MonthDate (datecalc: LocalDate,nBrig: Int):Double
              text = "\tБригада 4:"
                      +String.format("%5d",getShift500Date1Date2 (selection[0],selection[selection.lastIndex],4).toInt())
                      +" ("
                      +String.format("%5d",getShift500Date1Date2Night (selection[0],selection[selection.lastIndex],4).toInt())
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
        DialogButtonOK(onDismiss)
      }
    }
  }
}
