package com.childmathematics.android.basement.lib.webview.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    displayProgressBar: Boolean = false,
    onClick: () -> Unit
) {
    if (!displayProgressBar) {
        Button(
            modifier = modifier
                .width(280.dp)
                .height(50.dp),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(),
//            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.background),
            shape = RoundedCornerShape(25)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White
                )
            )
        }
    }else {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 6.dp
        )
    }
}