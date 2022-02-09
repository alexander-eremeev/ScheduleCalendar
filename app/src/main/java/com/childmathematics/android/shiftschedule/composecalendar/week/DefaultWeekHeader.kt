package com.childmathematics.android.shiftschedule.composecalendar.week

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import java.time.DayOfWeek
import java.time.format.TextStyle.SHORT
import java.util.Locale

@Composable
public fun DefaultWeekHeader(
  daysOfWeek: List<DayOfWeek>,
  modifier: Modifier = Modifier,
) {
/* --------------------------------------------------------------------------
*     Наименование дня недели
*/
//--------------------------------------------------------------------------
  Row(modifier = modifier) {
    daysOfWeek.forEach { dayOfWeek ->
      Text(
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
//        text = dayOfWeek.value ,
//        text = dayOfWeek.getDisplayName(SHORT, Locale.ROOT),
        text = NameDayWeekRus(dayOfWeek.value),
        modifier = modifier
          .weight(1f)
          .wrapContentHeight()
      )
    }
  }
}

internal fun <T> Array<T>.rotateRight(n: Int): List<T> = takeLast(n) + dropLast(n)
//--------------------------------------------------------------------------------

fun NameDayWeekRus(dayNumber: Int): String {
  if (dayNumber == 1) {
    return "Пн"
//    System.out.println("Сегодня - понедельник")
  }
  else if (dayNumber == 2) {
    return "Вт"
//    System.out.println("Сегодня - вторник")
  }
  else if (dayNumber == 3) {
    return "Ср"
//    System.out.println("Сегодня - среда")
  }
  else if (dayNumber == 4) {
    return "Чт"
      //    System.out.println("Сегодня - четверг")
  }
  else if (dayNumber == 5) {
    return "Пт"
//    System.out.println("Сегодня - пятница. Ура!!!")
  }
  else if (dayNumber == 6) {
    return "Сб"
//    System.out.println("Сегодня - суббота")
  }
  else if (dayNumber == 7) {
    return "Вс"
//    System.out.println("Сегодня - восскресенье")
  }
  else {
    return "Er"
//    System.out.println("Ошибка: такого дня недели нет")
  }
}

//-----------------------------------------------------------------------------