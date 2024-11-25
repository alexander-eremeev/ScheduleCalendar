package com.childmathematics.android.shiftschedule.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

// получить исходное «нормальное», немасштабированное значение:
val TextUnit.nonScaledSp
    @Composable
    get() = (this.value / LocalDensity.current.fontScale).sp