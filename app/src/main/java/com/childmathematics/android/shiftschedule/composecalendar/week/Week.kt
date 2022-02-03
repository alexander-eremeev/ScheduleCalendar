package com.childmathematics.android.shiftschedule.composecalendar.week

import androidx.compose.runtime.Immutable
import com.childmathematics.android.shiftschedule.composecalendar.day.Day

@Immutable
internal data class Week(
  val isFirstWeekOfTheMonth: Boolean = false,
  val days: List<Day>,
)
