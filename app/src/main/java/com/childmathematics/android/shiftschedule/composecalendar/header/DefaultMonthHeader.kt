package com.childmathematics.android.shiftschedule.composecalendar.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.childmathematics.android.shiftschedule.R

/**
 * Default implementation of month header, shows current month and year, as well as
 * 2 arrows for changing currently showed month
 *
 */
@Composable
public fun DefaultMonthHeader(
  monthState: MonthState,
  modifier: Modifier = Modifier,
) {
/* --------------------------------------------------------------------------
*     Обработка кнопки - месяц
*/
//--------------------------------------------------------------------------
  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center,
  ) {
    IconButton(
      modifier = Modifier.testTag("Decrement"),
      onClick = { monthState.currentMonth = monthState.currentMonth.minusMonths(1) }
    ) {
//      painter = painterResource(id = R.drawable.ic_shiftschedule),

      Image(
        imageVector = Icons.Default.KeyboardArrowLeft,
//        imageVector = R.drawable.ic_icons8_left_arrow_48,
        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
        contentDescription = "Previous",
      )
    }
/* --------------------------------------------------------------------------
*     Наименование месяца
*/
//--------------------------------------------------------------------------
    /*
    Text(
      modifier = Modifier.testTag("MonthLabel"),
      text = monthState.currentMonth.month.name.lowercase().replaceFirstChar { it.titlecase() },
      style = MaterialTheme.typography.h4
    )
    */
    Spacer(modifier = Modifier.width(8.dp))
    //   Text(text = monthState.currentMonth.year.toString(), style = MaterialTheme.typography.h4)
    Text(text = NameMonthRus(monthState.currentMonth.month.value)+ "  "+
            monthState.currentMonth.year.toString(), style = MaterialTheme.typography.h4)
/* --------------------------------------------------------------------------
*     Обработка кнопки + месяц NameMonthRus(monthState.currentMonth.year)
*/
//--------------------------------------------------------------------------
    IconButton(
      modifier = Modifier.testTag("Increment"),
      onClick = { monthState.currentMonth = monthState.currentMonth.plusMonths(1) }
    ) {
      Image(
        imageVector = Icons.Default.KeyboardArrowRight,
        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
        contentDescription = "Next",
      )
    }
  }
}
//--------------------------------------------------------------------------------

fun NameMonthRus(monthNumber: Int): String {
  if (monthNumber == 1) {
    return "Январь"
//    System.out.println("Сегодня - понедельник")
  }
  else if (monthNumber == 2) {
    return "Февраль"
//    System.out.println("Сегодня - вторник")
  }
  else if (monthNumber == 3) {
    return "Март"
//    System.out.println("Сегодня - среда")
  }
  else if (monthNumber == 4) {
    return "Апрель"
    //    System.out.println("Сегодня - четверг")
  }
  else if (monthNumber == 5) {
    return "Май"
//    System.out.println("Сегодня - пятница. Ура!!!")
  }
  else if (monthNumber == 6) {
    return "Июнь"
//    System.out.println("Сегодня - суббота")
  }
  else if (monthNumber == 7) {
    return "Июль"
  }
  else if (monthNumber == 8) {
    return "Август"
  }
  else if (monthNumber == 9) {
    return "Сентябрь"
  }
  else if (monthNumber == 10) {
    return "Октябрь"
  }
  else if (monthNumber == 11) {
    return "Ноябрь"
  }
  else if (monthNumber == 12) {
    return "Декабрь"
  }
  else {
    return "Error"
//    System.out.println("Ошибка: такого дня недели нет")
  }
}

//-----------------------------------------------------------------------------