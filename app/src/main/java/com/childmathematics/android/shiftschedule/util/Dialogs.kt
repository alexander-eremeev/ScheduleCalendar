package com.childmathematics.android.shiftschedule.util

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DialogButtonOK(onDismiss: () -> Unit) {
    Row {
        /*
        TextButton(
          onClick = onDismiss,
          modifier = Modifier.padding(8.dp)
        ) {
          Text(text = "Cancel")
        }
        */
        TextButton(
            onClick = onDismiss,
            modifier = Modifier.padding(30.dp)
//            modifier = Modifier.align(CenterVertically)
        ) {
            Text(text = "Далее",

                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                //          modifier = Modifier.padding(8.dp)
//              modifier = Modifier
//                   textAlign = Center
            )
        }
    }
}
