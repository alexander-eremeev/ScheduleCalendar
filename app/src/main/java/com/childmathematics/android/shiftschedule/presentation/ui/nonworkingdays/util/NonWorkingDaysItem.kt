package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.childmathematics.android.shiftschedule.data.models.NonWorkingDaysEntity

@Composable
fun NonWorkingDaysItem (nonWorkingDaysEnt: NonWorkingDaysEntity?) {
    Row(
        modifier = Modifier
        //        .fillMaxWidth(),
    ) {
        Text(nonWorkingDaysEnt?.day.toString()+"."+
                nonWorkingDaysEnt?.month.toString()+"."+nonWorkingDaysEnt?.year.
        toString(),textAlign = TextAlign.Right
            ,modifier = Modifier  .weight(.2f)  )
        Spacer(modifier = Modifier.width(10.dp))
        Text(nonWorkingDaysEnt?.name.toString(),textAlign = TextAlign.Left
            ,modifier = Modifier    .weight(.8f))
    }
}
