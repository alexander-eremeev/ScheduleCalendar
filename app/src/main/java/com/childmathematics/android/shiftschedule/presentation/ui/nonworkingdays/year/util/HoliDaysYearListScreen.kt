package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.year.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.NonWorkingDaysViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util.HoliDaysItem
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util.NonWorkingDaysItem

//-----------------------------------------
// Список праздничных дней
@Composable
fun HolyDaysYearListScreen (nonWorkingDaysViewModel: NonWorkingDaysViewModel) {
    val nonWorkingDaysViewState by nonWorkingDaysViewModel.viewState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()


    Box(
        modifier = Modifier
    ) {
        when {
            nonWorkingDaysViewState.isLoading -> CircularProgressIndicator(color =
                MaterialTheme.colorScheme.inversePrimary)
            /*
                        nonWorkingDaysViewState.nonWorkingDaysYear.isEmpty() && ! nonWorkingDaysViewState.isLoading ->
                            Text("нет данных в БД")
            */
            nonWorkingDaysViewState.holiDaysYear.count()>0 -> {
                // Содержимое таблицы
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        //                       .fillMaxSize()
                        .padding(horizontal = 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(
                        items = nonWorkingDaysViewState.holiDaysYear,
                        key = { index, item ->
                            index
                        },
                    ) { index, item ->
                        HoliDaysItem(nonWorkingDaysViewState.holiDaysYear[index] )
                    }
                }
            }
            else -> {
            }

        }
    }
}
