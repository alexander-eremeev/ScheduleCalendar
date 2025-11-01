package com.childmathematics.android.basement.lib.composecalendar.week

import androidx.compose.runtime.Immutable
import com.childmathematics.android.basement.lib.composecalendar.day.Day

@Immutable
internal data class WeekDays(
  val isFirstWeekOfTheMonth: Boolean = false,
  val days: List<Day>,
)
