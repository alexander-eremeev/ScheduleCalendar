package com.childmathematics.android.basement.lib.composecalendar.month

import android.annotation.SuppressLint
import androidx.annotation.IntRange
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.childmathematics.android.basement.lib.composecalendar.header.MonthState
import com.childmathematics.android.basement.lib.composecalendar.util.dec
import com.childmathematics.android.basement.lib.composecalendar.util.inc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.runningFold
import java.time.YearMonth

private const val pageCount = 3

// @ExperimentalCoroutinesApi
// @OptIn(ExperimentalPagerApi::class)
// @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)

@OptIn(ExperimentalFoundationApi::class)
@Stable
internal class MonthPagerState
@OptIn(ExperimentalFoundationApi::class)
constructor(
// internal class MonthPagerState @ExperimentalPagerApi constructor(
    coroutineScope: CoroutineScope,
    private val monthState: MonthState,
    private val pagerState: PagerState,
) {

    @OptIn(ExperimentalFoundationApi::class)
    private var monthProvider by mutableStateOf(
        MonthProvider(
            initialMonth = monthState.currentMonth,
            currentIndex = pagerState.currentPage,
        )
    )

    fun getMonthForIndex(@IntRange(from = 0, to = 2) index: Int) = monthProvider.cache[index]!!

    init {
        snapshotFlow { monthState.currentMonth }.onEach { month ->
            moveToMonth(month)
        }.launchIn(coroutineScope)

        snapshotFlow { pagerState.currentPage }.runningFold(1 to 1) { oldIndices, newIndex ->
            oldIndices.second to newIndex
        }.distinctUntilChanged().onEach { (oldIndex, newIndex) ->
            onScrolled(oldIndex, newIndex)
            monthState.currentMonth = getMonthForIndex(newIndex)
        }.launchIn(coroutineScope)
    }

    @SuppressLint("Range")
    private fun moveToMonth(month: YearMonth) {
        if (month - getMonthForIndex(pagerState.currentPage) == 0) return
        monthProvider = MonthProvider(monthState.currentMonth, pagerState.currentPage)
    }

    private fun onScrolled(oldIndex: Int, newIndex: Int) {
        when (oldIndex - newIndex) {
            -1, pageCount - 1 -> monthProvider.cache[(newIndex + 1) % pageCount] =
                monthProvider.cache[newIndex]!!.inc()
            else -> monthProvider.cache[(newIndex - 1 + pageCount) % pageCount] =
                monthProvider.cache[newIndex]!!.dec()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MonthPagerState

        if (monthState != other.monthState) return false
        if (pagerState != other.pagerState) return false
        if (monthProvider != other.monthProvider) return false

        return true
    }

    override fun hashCode(): Int {
        var result = monthState.hashCode()
        result = 31 * result + pagerState.hashCode()
        result = 31 * result + monthProvider.hashCode()
        return result
    }
}

@Stable
internal class MonthProvider(initialMonth: YearMonth, currentIndex: Int) {
    val cache = mutableStateMapOf<Int, YearMonth>()

    init {
        cache[(currentIndex - 1 + pageCount) % pageCount] = initialMonth.dec()
        cache[currentIndex] = initialMonth
        cache[(currentIndex + 1) % pageCount] = initialMonth.inc()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MonthProvider

        if (cache != other.cache) return false

        return true
    }

    override fun hashCode(): Int {
        return cache.hashCode()
    }
}

private operator fun YearMonth.minus(other: YearMonth) =
    year - other.year + month.value - other.month.value
