package com.childmathematics.android.shiftschedule

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.childmathematics.android.basement.lib.ads.util.detectTapAndPressUnconsumed
import com.childmathematics.android.basement.lib.ads.yandex.YANDEX_MOBILE_ADS_TAG
import com.childmathematics.android.basement.lib.ads.yandex.mYaInterstitialAdOnOff
import com.childmathematics.android.basement.lib.ads.yandex.yaAdsInterstutialTimerOff
import com.childmathematics.android.basement.lib.composecalendar.SelectableCalendar
import com.childmathematics.android.basement.lib.composecalendar.day.DayState
import com.childmathematics.android.basement.lib.composecalendar.rememberSelectableCalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionMode
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionMode.Period
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


@ExperimentalCoroutinesApi
@Composable
//fun Schedule500Sample() {
  fun Schedule01Sample(currentDialog: Boolean) {
//===========================================================================
    val viewModel = remember { Sch01RecipeViewModel() }
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var changeDp: Dp
    val state = rememberSelectableCalendarState(
        onSelectionChanged = viewModel::onSelectionChanged, //SelectionMode
        initialSelectionMode = Period,
    )
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

//------------------------------------------------
        Spacer(
            modifier = Modifier.height(20.dp)

        )
        Column(
            Modifier
                .padding(0.dp, changeDp, 0.dp, 0.dp)        // добавлен для баннера
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "График прерывный, односменный, 8 часовой с выходными СБ и ВС",
                modifier = Modifier
                    .align(CenterHorizontally),

                style = MaterialTheme.typography.body1,
//      color = MaterialTheme.colors.secondary,
                softWrap = true,
                fontStyle = Italic,
                maxLines = 2,
                textDecoration = TextDecoration.Underline,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))  //====================================================
        Column(
            Modifier
                .padding(0.dp, changeDp + 50.dp, 0.dp, 0.dp)        // добавлен для баннера
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
                        ""+ String.format(
                    "%4d", (getShift01WorkDayMonth(
                        state.monthState.currentMonth.year,
                        state.monthState.currentMonth.monthValue
                    ))
                )
                        + "\tраб.дн.\t"
                        + String.format(
                    "%4d", (getShift01Month(
                        state.monthState.currentMonth.year, state.monthState.currentMonth.monthValue
                    )).toInt()
                )


                        + "\tчас.\n",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,

                )
            //========================================================================
            Spacer(modifier = Modifier.height(20.dp))
            //--------------------------------------

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
        if (currentDialog != showDialog) {

            if (!state.selectionState.selection.isEmpty()) {
                DialogSchedule01(state.selectionState.selection) {
                    showDialog = !showDialog
                }
            }
            if (state.selectionState.selection.size == 0) {
                Toast.makeText(
                    LocalContext.current,
                    "Нет выделенных дат для расчета!! ",
                    Toast.LENGTH_LONG
                ).show()
                showDialog = !showDialog
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

  val isSelected = selectionState.isDateSelected(date)

  Card(
    modifier = modifier
      .aspectRatio(1f)
      .padding(2.dp),
    elevation = if (state.isFromCurrentMonth) 4.dp else 0.dp,
    border = if (state.isCurrentDay){ BorderStroke(1.dp, MaterialTheme.colors.primary)
//      if (state.isCurrentDay){ BorderStroke(2.dp, MaterialTheme.colors.primary)
                } else if (state.isCurrentDay && (date.dayOfWeek.value==6 || date.dayOfWeek.value==7))
                    BorderStroke(2.dp, MaterialTheme.colors.error)
                else if (state.isFromCurrentMonth && (date.dayOfWeek.value==6 || date.dayOfWeek.value==7))
                    BorderStroke(1.dp, MaterialTheme.colors.error)
//        else if (date.dayOfWeek.value==6 || date.dayOfWeek.value==7) BorderStroke(1.dp,
//      if (state.isCurrentDay){ BorderStroke(2.dp, MaterialTheme.colors.primary)
        else null,
    contentColor = if (isSelected) MaterialTheme.colors.secondary else contentColorFor(
      backgroundColor = MaterialTheme.colors.surface
    )
  ) {
    Column(
      modifier = Modifier
          //====================================================
          // фиксация нажатия экрана для сдвига паказа рекламы
          .pointerInput(Unit) {
              detectTapAndPressUnconsumed(onTap = {
                  Log.d(YANDEX_MOBILE_ADS_TAG, "Schedule01 Interstitial:select date TAP")
                  yaAdsInterstutialTimerOff()  //реклама через 180 cек  durationNoPushTastaturAds
              })
          }
          //--------------------------------------------------

          .clickable {
                        selectionState.onDateSelected(date)
      }
        ,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
            Text(
//                modifier = Modifier.background(androidx.compose.ui.graphics.Color.Red, CircleShape),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                text = date.dayOfMonth.toString(),
//          style = MaterialTheme.typography.h6,

            )
        if (date.dayOfWeek.value==6 || date.dayOfWeek.value==7) {
/*
            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.error)   //
//            .background(MaterialTheme.colors.secondary)   // blue
//            .background(MaterialTheme.colors.error)     // red
//            .background(Resources.Theme.ShiftCalendar.colors.)     // red
            )
*/
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
//          fontSize = 9.sp,
                style = MaterialTheme.typography.body2,
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
    style = MaterialTheme.typography.h6,
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
fun getShift01WorkDayMonth (year: Int,month: Int):Int {
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
fun getShift01Month (year: Int,month: Int):Double {
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
//====================================================================
// расчет основного времени между выбранной даты по номеру бригады с начала месяца
//==============================================
fun getShift01Date1Date2 (date1: LocalDate,date2: LocalDate):Double {
//  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1).minusDays(1)
    var dateforCalc: LocalDate= date2

    var summ: Double =getShift01(dateforCalc)
    for (i in dateforCalc.toEpochDay()-date1.toEpochDay() downTo 1 step 1) {
        summ += getShift01(dateforCalc.minusDays(i.toLong()))
    }
    return summ
}
//====================================================================
// расчет основного времени между выбранной даты по номеру бригады с начала месяца
//==============================================
fun getShift01Date1Date2Days (date1: LocalDate,date2: LocalDate):Int {
//  var dateforCalc: LocalDate= LocalDate.of(yearW,monthW,1).minusDays(1)
    var dateforCalc: LocalDate= date2

    var summDays: Int
    if (getShift01(dateforCalc)>0.0) summDays  =1 else summDays  =0
    for (i in dateforCalc.toEpochDay()-date1.toEpochDay() downTo 1 step 1) {
        if (getShift01(dateforCalc.minusDays(i.toLong())) >0.0)
            summDays  +=1


    }
    return summDays
}//====================================================================
@Composable
fun DialogSchedule01(selection: List<LocalDate>,onDismiss: () -> Unit) {


  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties()
  ) {
    Surface(elevation = 8.dp, shape = RoundedCornerShape(12.dp)) {
      Column(
        modifier = Modifier
            //====================================================
            // фиксация нажатия экрана для сдвига паказа рекламы
            .pointerInput(Unit) {
                detectTapAndPressUnconsumed(onTap = {
                    Log.d(YANDEX_MOBILE_ADS_TAG, "DialogSchedule01 Interstitial:select date TAP")
                    yaAdsInterstutialTimerOff()  //реклама через 180 cек  durationNoPushTastaturAds
                })
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

//------------------------------------------------------------------------------------------------------------------------------

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
                .align(CenterHorizontally),
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
          modifier = Modifier.align(CenterHorizontally) ,
//          textAlign= Center,
          fontSize = 20.sp,    )
        //--------------------------------------------getShift01Date1Date2 (date1: LocalDate,date2: LocalDate------------------
          Text(
              text = "\tОтработано:\n\t"
                      +String.format("%4d",(getShift01Date1Date2Days(selection[0],selection[selection.lastIndex])))
                      +"\tраб.дн.\t"
//                      + String.format("%4d",(getShift01MonthDate (selection[0],selection[selection.lastIndex]))
                      +String.format("%5d",getShift01Date1Date2 (selection[0],selection[selection.lastIndex]).toInt())
                      +"\tчас."
              ,
              fontSize = 15.sp,    )
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
