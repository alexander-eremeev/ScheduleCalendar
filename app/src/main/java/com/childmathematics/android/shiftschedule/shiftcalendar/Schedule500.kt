package com.childmathematics.android.shiftschedule

import android.graphics.fonts.FontFamily
import android.graphics.fonts.FontStyle
import android.graphics.fonts.FontStyle.FONT_WEIGHT_BOLD
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.childmathematics.android.shiftschedule.composecalendar.CalendarState
import com.childmathematics.android.shiftschedule.composecalendar.SelectableCalendar
import com.childmathematics.android.shiftschedule.composecalendar.day.DayState
import com.childmathematics.android.shiftschedule.composecalendar.rememberSelectableCalendarState
import com.childmathematics.android.shiftschedule.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.shiftschedule.composecalendar.selection.SelectionMode
import com.childmathematics.android.shiftschedule.composecalendar.selection.SelectionMode.Period
import com.childmathematics.android.shiftschedule.composecalendar.selection.SelectionMode.Single
import com.google.android.material.datepicker.MaterialCalendar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import java.lang.Long.getLong
import java.time.*
import java.util.*

/**
 * In this sample, calendar composable is wired with an ViewModel. It's purpose is to show how to use
 * the composable in real world use-case, by an example implementation of a calendar
 * which can display planned recipes along with their prices
 */
@ExperimentalCoroutinesApi
@Composable
fun Schedule500Sample() {
  val viewModel = remember { Sch500RecipeViewModel() }
//  val recipes by viewModel.recipesFlow.collectAsState()
//  val selectedPrice by viewModel.selectedRecipesPriceFlow.collectAsState(0)
  var changeDp: Dp
  val state = rememberSelectableCalendarState(
    onSelectionChanged = viewModel::onSelectionChanged, //SelectionMode
//    onSelectionChanged = viewModel::SelectionMode.Single, //SelectionMode
//    initialSelectionMode = Period,
    initialSelectionMode = Single,
  )
  if(BuildConfig.AdMobEnable|| BuildConfig.YaAdsEnable) {
    changeDp = 50.dp
  } else changeDp = 0.dp
//-------------------------------------------------
  Spacer(modifier = Modifier.height(20.dp))
  Column(
    Modifier
      .padding(0.dp,changeDp,0.dp,0.dp)        // добавлен для баннера
    ) {
    Text(
      textAlign = TextAlign.Center,
      text = "График непрерывный 12 часовой 4-х бригадный 2-х сменный "      ,
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
      .verticalScroll(rememberScrollState())
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

    Spacer(modifier = Modifier.height(20.dp))
//    selectedPrice = getShift500NightSelect(date.CurrentDay,1)
//   state.monthState.currentMonth.month
//    state.monthState.currentMonth.year
//    YearMonth.

    Text(
//      text = "Selected recipes price: $selectedPrice",
      text = "ВСЕГО   Бригада 1:"+"Ночных:"+ (getShift500NightMonth(state.monthState.currentMonth.year,
            state.monthState.currentMonth.monthValue,1)).toInt().toString()+"/ Рабочих:"+(getShift500Month(
            state.monthState.currentMonth.year,state.monthState.currentMonth.monthValue,1)).toInt().toString()+" час"
//              +"/"+state.monthState.currentMonth.monthValue+"/"+state.monthState.currentMonth.year
      ,
//      style = MaterialTheme.typography.h6,
      fontSize = 12.sp,    )
    Text(
//      text = "Selected recipes price: $selectedPrice",
//      text = "ВСЕГО ЗА МЕСЯЦ Бригада 2:"+"Ночные:"+ (getShift500NightMonth(state.monthState.currentMonth.year,
      text = "                Бригада 2:"+"Ночных:"+ (getShift500NightMonth(state.monthState.currentMonth.year,
        state.monthState.currentMonth.monthValue,2)).toInt().toString()+"/ Рабочих:"+(getShift500Month(
        state.monthState.currentMonth.year,state.monthState.currentMonth.monthValue,2)).toInt().toString()+" час",
      fontSize = 12.sp,
      //style = MaterialTheme.typography.h6,
    )
    Text(
//      text = "Selected recipes price: $selectedPrice",
      text = "                Бригада 3:"+"Ночных:"+ (getShift500NightMonth(state.monthState.currentMonth.year,
        state.monthState.currentMonth.monthValue,3)).toInt().toString()+"/ Рабочих:"+(getShift500Month(
        state.monthState.currentMonth.year,state.monthState.currentMonth.monthValue,3)).toInt().toString()+" час",
      fontSize = 12.sp,
      //      style = MaterialTheme.typography.h6,
    )
    Text(
//      text = "Selected recipes price: $selectedPrice",
      text = "                Бригада 4:"+"Ночных:"+ (getShift500NightMonth(state.monthState.currentMonth.year,
        state.monthState.currentMonth.monthValue,4)).toInt().toString()+"/ Hабочих:"+(getShift500Month(
        state.monthState.currentMonth.year,state.monthState.currentMonth.monthValue,4)).toInt().toString()+" час",
//      style = MaterialTheme.typography.h6,
//      style = MaterialTheme.typography.h6,
      fontSize = 12.sp,
    )

    Spacer(modifier = Modifier.height(20.dp))
  }
}

/**
 * Custom implementation of DayContent, which shows a dot
 * if there is an recipe planned for this day.
 * Пользовательская реализация DayContent,
 * которая показывает точку, если на этот день запланирован рецепт.
 */
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
    elevation = if (state.isFromCurrentMonth) 4.dp else 0.dp,
    border = if (state.isCurrentDay) BorderStroke(1.dp, MaterialTheme.colors.primary) else null,
    contentColor = if (isSelected) MaterialTheme.colors.secondary else contentColorFor(
      backgroundColor = MaterialTheme.colors.surface
    )
  ) {
    Column(
      modifier = Modifier.clickable {
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
          text = (getShift500(date,1)).toInt().toString()+" / "+(getShift500(date,2)).toInt().toString(),
          fontSize = 9.sp,
//          style = MaterialTheme.typography.body2,
        )
        Text(
//          text = plannedRecipe.price.toString(),
          text = (getShift500(date,3)).toInt().toString()+" / "+(getShift500(date,4)).toInt().toString(),
          fontSize = 9.sp,
        )
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
    Log.d("Schedule500", "SelectionControls")
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

data class Sch500PlannedRecipe(
  val date: LocalDate,
  val price: Double,
)

/**
 * ViewModel exposing list of our recipes
 */
class Sch500RecipeViewModel : ViewModel() {


  private val selectionFlow = MutableStateFlow(emptyList<LocalDate>())
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

  fun onSelectionChanged(selection: List<LocalDate>) {
//    fun onSelectionChanged(selection:LocalDate) {
    //selectionFlow.value = selection
    if (BuildConfig.DEBUG) {
      Log.d("Schedule500", "onSelectionChanged: "+selection[0].dayOfMonth+"/"
      +selection[0].monthValue+"/"+selection[0].year)    }
  }
}

@ExperimentalCoroutinesApi
@Preview
@Composable
fun Schedule500SamplePreview() {
  MaterialTheme {
    Schedule500Sample()
  }
}
//====================================================================
// расчетосновного рабочего времени по дате по номеру бригады
//==============================================
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
// расчет ночных до выбранной даты по номеру бригады
//==============================================
fun getShift500NightSelect(dateforCalc: LocalDate,nBrig: Int):Double {
  var summ: Double =getShift500Night(dateforCalc ,nBrig)
  for (i in dateforCalc.dayOfMonth downTo 1 step 1) {
    summ+=getShift500Night(dateforCalc.minusDays(i.toLong()) ,nBrig)
  }
return summ
}
//====================================================================
// расчет основного времени до выбранной даты по номеру бригады
//==============================================
fun getShift500Select (dateforCalc: LocalDate,nBrig: Int):Double {
  var summ: Double =getShift500(dateforCalc ,nBrig)
  for (i in dateforCalc.dayOfMonth downTo 1 step 1) {
    summ+=getShift500(dateforCalc.minusDays(i.toLong()) ,nBrig)
  }
  return summ
}//====================================================================
//====================================================================
// расчет ночных до выбранной даты по номеру бригады
//==============================================
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
