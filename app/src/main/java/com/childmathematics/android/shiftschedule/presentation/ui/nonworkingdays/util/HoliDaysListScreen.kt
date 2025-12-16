package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.NonWorkingDaysViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.uimodels.NonWorkingDaysViewState

// Список праздничных дней
@Composable
fun HoliDaysListScreen (nonWorkingDaysViewState: NonWorkingDaysViewState
//    nonWorkingDaysViewModel: NonWorkingDaysViewModel,
    ) {
//    val nonWorkingDaysViewState by nonWorkingDaysViewModel.viewState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val scrollState: ScrollState = rememberScrollState(1)
/*
    Box(
        modifier = Modifier
        //           .weight(1f)
        //           .fillMaxWidth(),
        //       contentAlignment = Alignment.TopCenter
    ) {

 */
    when {
        nonWorkingDaysViewState.isLoading -> CircularProgressIndicator(color =
            MaterialTheme.colorScheme.inversePrimary)
        /*
                    nonWorkingDaysViewState.nonWorkingDays.isEmpty() && ! nonWorkingDaysViewState.isLoading ->
                        Text("Нет данных в БД")
        */
        nonWorkingDaysViewState.holiDays.count()>0 -> {

                // Содержимое таблицы
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .height( (LocalConfiguration.current.screenHeightDp/5).dp)//LocalConfiguration.current.screenHeightDp

//                        .verticalScroll(rememberScrollState())
//                        .size( 200. dp)
                        //                       .fillMaxSize()
                        .padding(horizontal = 0.dp),
                    
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    itemsIndexed(
                        items = nonWorkingDaysViewState.holiDays,
                        key = { index, item ->
                            index
                        },
                    ) { index, item ->
                        HoliDaysItem(nonWorkingDaysViewState.holiDays[index] )
                    }
                }
            }
        }
}
