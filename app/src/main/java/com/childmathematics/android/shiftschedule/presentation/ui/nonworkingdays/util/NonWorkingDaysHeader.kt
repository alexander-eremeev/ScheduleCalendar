package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

//     // Заголовоки столбцов таблицы
@Composable
fun HoliDaysHeader()
{
    // Заголовоки столбцов таблицы
    Text("Праздничные дни",textAlign = TextAlign.Center ,color = Color.Red, fontWeight = Bold,
        modifier = Modifier .padding(horizontal = 20.dp))
/*
    Row(
        modifier = Modifier
            //        .fillMaxWidth(),
            .padding(horizontal = 0.dp),
    ) {
        Text("Дата",textAlign = TextAlign.Center ,modifier = Modifier .weight(.2f))
        Spacer(modifier = Modifier.width(10.dp))
        Text("Наименование",textAlign = TextAlign.Left
            ,modifier = Modifier     .weight(.8f))
    }

 */
}
@Composable
fun NonWorkingDaysHeader()
{
    // Заголовоки столбцов таблицы
    Text("Нерабочие дни",textAlign = TextAlign.Center ,color = Color.DarkGray, fontWeight = Bold,
        modifier = Modifier .padding(horizontal = 20.dp))
/*
    Row(
        modifier = Modifier
            //        .fillMaxWidth(),
            .padding(horizontal = 0.dp),
    ) {

        Text("Дата",textAlign = TextAlign.Center ,modifier = Modifier .weight(.2f))
        Spacer(modifier = Modifier.width(10.dp))
        Text("Наименование",textAlign = TextAlign.Left
            ,modifier = Modifier     .weight(.8f))
    }
 */
}
