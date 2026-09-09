package com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays

import android.provider.CalendarContract
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues.Companion.Zero
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.childmathematics.android.basement.lib.ads.ui.theme.Shapes
import com.childmathematics.android.basement.lib.composecalendar.CalendarState
import com.childmathematics.android.basement.lib.composecalendar.SelectableCalendar
import com.childmathematics.android.basement.lib.composecalendar.day.DayState
import com.childmathematics.android.basement.lib.composecalendar.rememberSelectableCalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.DynamicSelectionState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionMode
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionState
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.data.models.CountryEntity
import com.childmathematics.android.shiftschedule.data.models.NonWorkingDaysEntity
import com.childmathematics.android.shiftschedule.presentation.ui.countries.CountriesViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.countries.CountryHeader
import com.childmathematics.android.shiftschedule.presentation.ui.countries.CountryListScreen
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.uimodels.NonWorkingDaysViewIntent
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util.NonWorkingDaysHeader
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.util.NonWorkingDaysListScreen
import com.childmathematics.android.shiftschedule.presentation.util.SnackbarEffect
import com.childmathematics.android.shiftschedule.util.bannerHightMin
import com.childmathematics.android.shiftschedule.util.bannerHightPlus
import com.childmathematics.android.shiftschedule.util.bannerHightWithVideoMin
import com.childmathematics.android.shiftschedule.util.nonScaledSp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
import java.util.Calendar

@ExperimentalCoroutinesApi
@Composable
fun NonWorkingDaysPage(
    nonWorkingDaysViewModel: NonWorkingDaysViewModel
)
{
//-------------------------------------------------------------------------
    val nonWorkingDaysViewState by nonWorkingDaysViewModel.viewState.collectAsStateWithLifecycle()

    // State to manage Snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    // Collect snackbar events and show snackbar
    LaunchedEffect(nonWorkingDaysViewModel) {
        nonWorkingDaysViewModel.effectFlow.collectLatest { effect ->
            if (effect is SnackbarEffect.ShowSnackbar) {
                val result = snackbarHostState.showSnackbar(
                    message = effect.message,
                    actionLabel = effect.actionLabel
                )
                if (result == SnackbarResult.ActionPerformed && effect.actionLabel == "Undo") {
//                    viewModel.handleIntent(Intent.UndoDelete)
                }
            }
        }
    }
    //-------------------------------------------------------------------------------
    var state = rememberSelectableCalendarState(
        initialSelectionMode = SelectionMode.Period,
    )
    val year = state.monthState.currentMonth.year
    val month = state.monthState.currentMonth.monthValue

    nonWorkingDaysViewModel.handleIntent(NonWorkingDaysViewIntent.LoadNonWorkingDays(year,month))

    var changeDp: Dp

    //------------------------
    var changeHightDp: Int
    var screenHeightDp: Int

    screenHeightDp = LocalConfiguration.current.screenHeightDp
    changeHightDp = screenHeightDp
    if( BuildConfig.YaAdsEnable) {
        if (screenHeightDp > 800)
            changeHightDp = screenHeightDp- bannerHightWithVideoMin - bannerHightPlus
        else changeHightDp = screenHeightDp- bannerHightMin - bannerHightPlus
    }
//----------------------------------
    changeDp = 0.dp
//=========================
     Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = {  paddingValues  ->
            Column(
                modifier = Modifier
                    .padding(paddingValues= Zero)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background, shape = Shapes.medium)
            ) {
//---------------------------------------------------------------
                Box(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState(changeHightDp))
                ) {
                    Column(
                        Modifier
                            .padding(0.dp, changeDp + 0.dp, 0.dp, 0.dp)        // добавлен для баннера
                    ) {
                        SelectableCalendar(
                            calendarState = state,
                            dayContent = { dayState ->
                                Sch01RecipeDay(
                                    state = dayState,
                                )
                            }
                        )
                    }
                }
//------------------------------------------------
                if (nonWorkingDaysViewState.nonWorkingDays.count()>0) {
                    NonWorkingDaysHeader()
                    NonWorkingDaysListScreen (nonWorkingDaysViewState)
                }
            }
        }
    )

//------------------------------
}

/**
 * Custom implementation of DayContent, which shows a dot
 * if there is an recipe planned for this day.
 * Пользовательская реализация DayContent,
 * которая показывает точку, если на этот день запланирован рецепт.
 */
@Composable
fun Sch01RecipeDay(
    state: DayState<DynamicSelectionState>,
    modifier: Modifier = Modifier,
) {
    val date = state.date
    val selectionState = state.selectionState
    var colorsCard : CardColors

    val isSelected = selectionState.isDateSelected(date)

    colorsCard = CardDefaults.cardColors()

    if (state.isCurrentDay)
        colorsCard = CardDefaults.cardColors(
            containerColor = Color.Green, //Card background color
            contentColor = Color.White  //Card content color,e.g.text
        )
    if (isSelected)
        colorsCard = CardDefaults.cardColors(
            containerColor = Color.Cyan, //Card background color
        )

    Card(
        onClick = {selectionState.onDateSelected(date)},
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp),
        enabled = true,
        border = if (state.isCurrentDay && (date.dayOfWeek.value==6 || date.dayOfWeek.value==7))
            BorderStroke(3.dp, MaterialTheme.colorScheme.error)
        else if(state.isCurrentDay){ BorderStroke(3.dp, MaterialTheme.colorScheme.primary)}
        else if (state.isFromCurrentMonth && (date.dayOfWeek.value==6 || date.dayOfWeek.value==7))
            BorderStroke(1.dp, MaterialTheme.colorScheme.error)
        else null,
        colors = colorsCard
    ) {
        Column(
            modifier = Modifier
                .align(CenterHorizontally)
                //====================================================
                // фиксация нажатия экрана для сдвига паказа рекламы
                .pointerInput(Unit) {
                    /*
                    detectTapAndPressUnconsumed(onTap = {
                        Log.d(YANDEX_MOBILE_ADS_TAG, "NonWorkingDays Interstitial:select date TAP")
                        yaAdsInterstutialTimerOff()  //реклама через 180 cек  durationNoPushTastaturAds
                    })
      
                     */
                }
                //--------------------------------------------------
                .clickable (true){
                    selectionState.onDateSelected(date)
                }
            ,
            horizontalAlignment = CenterHorizontally,
        ) {
            Text(
//                modifier = Modifier.background(androidx.compose.ui.graphics.Color.Red, CircleShape),
                fontSize = 20.sp.nonScaledSp,       //15.sp
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                text = date.dayOfMonth.toString(),
                style = MaterialTheme.typography.bodyLarge,

                )
            if (date.dayOfWeek.value==6 || date.dayOfWeek.value==7) {
                Text(
                    text = "",
                )
            }
            else {
                Text(
//          text = plannedRecipe.price.toString(),
                    text = //String.format("%2d",(getShift01(date)).toInt())
//                  +" / "+
                    String.format("%2d", (getShift01(date)).toInt()),
                    fontSize = 15.sp.nonScaledSp,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

data class Sch01PlannedRecipe(
    val date: LocalDate,
    val price: Double,
)
//====================================================================
// расчет основного рабочего времени по дате по номеру бригады
//==============================================
fun getShift01 (dateforCalc: LocalDate):Double
{
    val shift : Int
    shift=dateforCalc.dayOfWeek.value

    when (shift) {
        1 -> return 8.0
        2 -> return 8.0
        3 -> return 8.0
        4 -> return 8.0
        5 -> return 8.0
        6 -> return 0.0
        7 -> return 0.0
        else -> return 099.0
    }
}
