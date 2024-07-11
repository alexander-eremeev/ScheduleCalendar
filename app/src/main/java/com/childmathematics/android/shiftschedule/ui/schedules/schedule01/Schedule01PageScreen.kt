package com.childmathematics.android.shiftschedule.ui.schedules.schedule01

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.childmathematics.android.basement.lib.composecalendar.rememberSelectableCalendarState
import com.childmathematics.android.basement.lib.composecalendar.selection.SelectionMode
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.ui.AppViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@Composable
internal fun Schedule01PageScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onOpenDrawer: Boolean,
    openDrawer: () -> Unit,
    navigateToSchedule01SummingPage: () -> Unit,
//    state: CalendarState<DynamicSelectionState>,

    /*
    navigateToHelpSchedule01Page: () -> Unit,
    navigateToHelpGraphicsPage: () -> Unit,
    navigateToHelpAboutPage: () -> Unit
     */
    schedule01PageViewModel: Schedule01PageViewModel = viewModel()
 //           viewModel: Schedule01PageViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    /*
    val state = rememberSelectableCalendarState(
//.        onSelectionChanged = viewModel::onSelectionChanged, //SelectionMode
//        confirmSelectionChange = viewModel::onSelectionChanged, //SelectionMode

        initialSelectionMode = SelectionMode.Period,
    )

     */

    ScheduleCalendarTheme {
        /*
 Собирает значения из этого StateFlow и представляет его последнее значение через State. StateFlow.value
 используется в качестве начального значения. Каждый раз, когда в StateFlow будет отправляться новое значение,
 возвращаемое состояние будет обновляться, вызывая рекомпозицию каждого использования State.value.
  */
  //      val schedule01PageUiState by viewModel.schedule01PageUiState.collectAsState()
        /*
        Возвращает TopAppBarScrollBehavior. Верхняя панель приложения, настроенная с помощью этого
        TopAppBarScrollBehavior, немедленно свернется при извлечении содержимого и сразу же появится при перемещении
        содержимого вниз.
        Параметры:
            состояние — объект состояния, который будет использоваться для управления или наблюдения за
                состоянием прокрутки верхней панели приложения. См. RememberTopAppBarState, чтобы узнать о состоянии,
                которое запоминается в композициях.
            canScroll — обратный вызов, используемый для определения того, должны ли события прокрутки обрабатываться
                этим EnterAlwaysScrollBehavior.
            snapAnimationSpec — необязательный AnimationSpec, который определяет, как верхняя панель приложения
                привязывается к полностью свернутому или полностью развернутому состоянию, когда перетаскивание или
                перетаскивание прокручивают ее в промежуточное положение.
            flingAnimationSpec — необязательный DecayAnimationSpec, определяющий, как отображать верхнюю
                панель приложения, когда пользователь перемещает саму панель приложения или содержимое под ней.
         */

        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        Scaffold(
            modifier = modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                Schedule01PageTopAppBar(
                    onOpenDrawer,
                    openDrawer,
                    onBackClick,scrollBehavior,
//                    navigateToSchedule01SummingPage ,
                    navigateToSchedule01SummingPage = navigateToSchedule01SummingPage,

                    /*
                    navigateToHelpSchedule01Page,
                    navigateToHelpGraphicsPage,
                    navigateToHelpAboutPage

                     */
                )
            },
            content ={ padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {

    //            schedule01PageUiState.state = rememberSelectableCalendarState(
                    var state = rememberSelectableCalendarState(
                       initialSelectionMode = SelectionMode.Period,
                     )



                    Schedule01Page(true,state)
//                    schedule01PageUiState.vmselection= state.selectionState.selection

                    //Schedule01Page(true,schedule01PageUiState.state)
                  }
            },
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Schedule01PageTopAppBar(
    onOpenDrawer: Boolean,
    openDrawer: () -> Unit,
      onBackClick: () -> Unit,
      scrollBehavior: TopAppBarScrollBehavior,
    navigateToSchedule01SummingPage: () -> Unit,

    /*
    navigateToHelpSchedule01Page: () -> Unit,
    navigateToHelpGraphicsPage: () -> Unit,
    navigateToHelpAboutPage: () -> Unit

     */
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.schedule01_titlemidle))
        },
        navigationIcon = {
            if(onOpenDrawer) {
                IconButton(onClick = openDrawer) {
                    Icon(Icons.Filled.Menu, stringResource(id = R.string.back_button))
                }
            }
            else {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Filled.Menu, stringResource(id = R.string.back_button))
                }
            }
        },
        scrollBehavior = scrollBehavior,

        actions = {
/*
            Schedule01PageMenu(
                navigateToSchedule01SummingPage = navigateToSchedule01SummingPage,
            )

 */


        },


        modifier = Modifier.fillMaxWidth()
    )
}
//-------------------------
@Composable
private fun Schedule01PageMenu(
    navigateToSchedule01SummingPage: () -> Unit,
    /*
navigateToHelpGraphicsPage: () -> Unit,
navigateToHelpAboutPage: () -> Unit,

 */
) {
    Schedule01PageTopAppBarDropdownMenu(
        iconContent = {
            Icon(
                Icons.Default.MoreVert,
                stringResource(id = R.string.schedule01_SummingSelectedDays)
            )
        }
    )

    { closeMenu ->

        DropdownMenuItem(
//            leadingIcon = {Icon(imageVector = Icons.AutoMirrored.Filled.ViewList, contentDescription = null)}
            leadingIcon = { Icon(painter = painterResource(R.drawable.functions_24px) , contentDescription =null )}
            ,
            onClick = {
                navigateToSchedule01SummingPage()
                closeMenu()
            },
            text = { Text(text = stringResource(id = R.string.schedule01_SummingSelectedDays)) }
        )
        /*
        DropdownMenuItem(
            leadingIcon = {Icon(imageVector = Icons.Filled.AppRegistration, contentDescription = null)}
            ,
            onClick = {
//                navigateToHelpGraphicsPage()
                closeMenu()
            },
            text = { Text(text = stringResource(id = R.string.help_Graphics)) }
        )
         */
    }


}
//-----------------------------
@Composable
private fun Schedule01PageTopAppBarDropdownMenu(
    iconContent: @Composable () -> Unit,
    content: @Composable ColumnScope.(() -> Unit) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
        IconButton(onClick = { expanded = !expanded }) {
            iconContent()
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentSize(Alignment.TopEnd)
        ) {
            content { expanded = !expanded }
        }
    }
}

/*
@Preview
@Composable
private fun HelpScreenPreview(
    ) {
    HelpPageScreen(
        onBackClick={}
    )
}
*/