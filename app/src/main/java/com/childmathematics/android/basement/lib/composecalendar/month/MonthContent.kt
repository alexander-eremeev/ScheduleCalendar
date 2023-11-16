package com.childmathematics.android.basement.lib.composecalendar.month

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.childmathematics.android.basement.lib.composecalendar.day.DayState
import com.childmathematics.android.basement.lib.composecalendar.header.MonthState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionState
import com.childmathematics.android.basement.lib.composecalendar.week.WeekContent
import com.childmathematics.android.basement.lib.composecalendar.week.getWeeks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

internal const val DaysOfWeek = 7

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalCoroutinesApi
@Composable
internal fun <T : SelectionState> MonthPager(
    showAdjacentMonths: Boolean,
    selectionState: T,
    monthState: MonthState,
    daysOfWeek: List<DayOfWeek>,
    today: LocalDate,
    modifier: Modifier = Modifier,
    dayContent: @Composable BoxScope.(DayState<T>) -> Unit,
    weekHeader: @Composable BoxScope.(List<DayOfWeek>) -> Unit,
    monthContainer: @Composable (content: @Composable (PaddingValues) -> Unit) -> Unit
) {
//  val pagerState = rememberPagerState(pageCount = 3, initialPage = 1, infiniteLoop = true)
//  val pagerState = androidx.compose.foundation.pager.rememberPagerState(initialPage = 1)
//    val pagerState = rememberPagerState(1)
    val pagerState = rememberPagerState(1, 0f) { 3 }
    val coroutineScope = rememberCoroutineScope()

    val monthPagerState = remember {
        MonthPagerState(
            coroutineScope = coroutineScope,
            monthState = monthState,
            pagerState = pagerState,
        )
    }

//    HorizontalPager(
    HorizontalPager(
        modifier = modifier.testTag("MonthPager"),
        state = pagerState,
        verticalAlignment = Alignment.Top,
//        count = 3
//            pageCount = 3
        beyondBoundsPageCount = 3
    ) { pageIndex ->
        MonthContent(
            showAdjacentMonths = showAdjacentMonths,
            selectionState = selectionState,
            currentMonth = monthPagerState.getMonthForIndex(pageIndex),
            today = today,
            daysOfWeek = daysOfWeek,
            dayContent = dayContent,
            weekHeader = weekHeader,
            monthContainer = monthContainer
        )
    }
}

@Composable
internal fun <T : SelectionState> MonthContent(
    showAdjacentMonths: Boolean,
    selectionState: T,
    currentMonth: YearMonth,
    daysOfWeek: List<DayOfWeek>,
    today: LocalDate,
    modifier: Modifier = Modifier,
    dayContent: @Composable BoxScope.(DayState<T>) -> Unit,
    weekHeader: @Composable BoxScope.(List<DayOfWeek>) -> Unit,
    monthContainer: @Composable (content: @Composable (PaddingValues) -> Unit) -> Unit,
) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            content = { weekHeader(daysOfWeek) },
        )

        monthContainer { paddingValues ->
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(paddingValues)
            ) {
                currentMonth.getWeeks(
                    includeAdjacentMonths = showAdjacentMonths,
                    firstDayOfTheWeek = daysOfWeek.first(),
                    today = today,
                ).forEach { week ->
                    WeekContent(
                        week = week,
                        selectionState = selectionState,
                        dayContent = dayContent,
                    )
                }
            }
        }
    }
}
