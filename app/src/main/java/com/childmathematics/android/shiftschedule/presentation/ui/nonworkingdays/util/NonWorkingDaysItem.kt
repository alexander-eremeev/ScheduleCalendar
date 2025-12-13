package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.childmathematics.android.shiftschedule.data.models.NonWorkingDaysEntity

@Composable
fun NonWorkingDaysItem (nonWorkingDaysEnt: NonWorkingDaysEntity?) {
    Row(
        modifier = Modifier
//            .fillMaxWidth(),
//            .horizontalScroll(rememberScrollState())

    ) {
/*
        Text(nonWorkingDaysEnt?.day.toString()+"."+
                nonWorkingDaysEnt?.month.toString()+"."+nonWorkingDaysEnt?.year.
        toString(),textAlign = TextAlign.Right
            ,modifier = Modifier  .weight(.2f)  )
        Spacer(modifier = Modifier.width(10.dp))
        Text(nonWorkingDaysEnt?.name.toString(),textAlign = TextAlign.Left
            ,modifier = Modifier    .weight(.8f))
*/

//----------------------------------------------------------
        Text(nonWorkingDaysEnt?.day.toString()+"."+
                nonWorkingDaysEnt?.month.toString()+"."+nonWorkingDaysEnt?.year.
            toString(),textAlign = TextAlign.Right,color = Color.DarkGray
            ,modifier = Modifier  .weight(.3f)  )
        Spacer(modifier = Modifier.width(3.dp))
        Text(nonWorkingDaysEnt?.name.toString(),textAlign = TextAlign.Left
            ,modifier = Modifier    .weight(.25f) .horizontalScroll(rememberScrollState()))
        Spacer(modifier = Modifier.width(3.dp))
        Text(nonWorkingDaysEnt?.moveDateYMD.toString().substring(6,8)+"."+
                nonWorkingDaysEnt?.moveDateYMD.toString().substring(4,6)
            ,textAlign = TextAlign.Left
            ,modifier = Modifier    .weight(.15f))

        Spacer(modifier = Modifier.width(5.dp))
        Text(nonWorkingDaysEnt?.reason.toString(),textAlign = TextAlign.Left
            ,modifier = Modifier    .weight(.30f) .horizontalScroll(rememberScrollState()))

//-------------------------------------------------------------

    }
}
