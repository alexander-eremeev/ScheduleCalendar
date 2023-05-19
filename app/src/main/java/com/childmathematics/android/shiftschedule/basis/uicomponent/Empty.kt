package com.childmathematics.android.shiftschedule.basis.uicomponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.childmathematics.android.shiftschedule.basis.theme.AlphaDisabled

@Composable
fun PgEmpty(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxWidth()) {
        Text(
            text,
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = AlphaDisabled)
        )
    }
}
