package com.childmathematics.android.basement.lib.composecalendar.week

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import java.time.DayOfWeek
import java.time.format.TextStyle.SHORT
import java.util.Locale
import kotlin.DeprecationLevel.WARNING

@Composable
@Deprecated(
  level = WARNING,
  replaceWith = ReplaceWith(
    "DefaultDaysOfWeekHeader(daysOfWeek, modifier)",
    "com.childmathematics.android.basement.lib.composecalendar.week.DefaultDaysOfWeekHeader",
  ),
  message = "Replace with DefaultDaysOfWeekHeader, DefaultWeekHeader will be removed in future versions"
)
public fun DefaultWeekHeader(
  daysOfWeek: List<DayOfWeek>,
  modifier: Modifier = Modifier,
) {
  Row(modifier = modifier) {
    daysOfWeek.forEach { dayOfWeek ->
      Text(
        textAlign = TextAlign.Center,
        text = dayOfWeek.getDisplayName(SHORT, Locale.getDefault()),
        modifier = modifier
          .weight(1f)
          .wrapContentHeight()
      )
    }
  }
}

@Composable
public fun DefaultDaysOfWeekHeader(
  daysOfWeek: List<DayOfWeek>,
  modifier: Modifier = Modifier,
) {
  Row(modifier = modifier) {
    daysOfWeek.forEach { dayOfWeek ->
      Text(
        textAlign = TextAlign.Center,
        text = dayOfWeek.getDisplayName(SHORT, Locale.getDefault()),
        modifier = modifier
          .weight(1f)
          .wrapContentHeight()
      )
    }
  }
}

internal fun <T> Array<T>.rotateRight(n: Int): List<T> = takeLast(n) + dropLast(n)
