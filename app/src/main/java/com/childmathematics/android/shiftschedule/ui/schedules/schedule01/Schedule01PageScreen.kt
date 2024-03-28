package com.childmathematics.android.shiftschedule.ui.schedules.schedule01

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.LocalPolice
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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.ui.AppViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@Composable
internal fun Schedule01PageScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    /*
    navigateToHelpSchedule01Page: () -> Unit,
    navigateToHelpGraphicsPage: () -> Unit,
    navigateToHelpAboutPage: () -> Unit
     */
    viewModel: Schedule01PageViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    ScheduleCalendarTheme {
        /*
 Собирает значения из этого StateFlow и представляет его последнее значение через State. StateFlow.value
 используется в качестве начального значения. Каждый раз, когда в StateFlow будет отправляться новое значение,
 возвращаемое состояние будет обновляться, вызывая рекомпозицию каждого использования State.value.
  */
        val schedule01PageUiState by viewModel.schedule01PageUiState.collectAsState()
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
                    onBackClick,scrollBehavior,
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
                    Schedule01Page(true)
                  }
            },
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Schedule01PageTopAppBar(
                          onBackClick: () -> Unit,
                          scrollBehavior: TopAppBarScrollBehavior,
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
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.Menu, stringResource(id = R.string.back_button))
                    }
        },
        scrollBehavior = scrollBehavior,
        /*
        actions = {
            HelpMenu( navigateToHelpSchedule01Page,
                navigateToHelpGraphicsPage,
                navigateToHelpAboutPage,
            )
        },

         */
        modifier = Modifier.fillMaxWidth()
    )
}
//-------------------------
@Composable
private fun Schedule01PageMenu(
    /*
    navigateToHelpSchedule01Page: () -> Unit,
    navigateToHelpGraphicsPage: () -> Unit,
    navigateToHelpAboutPage: () -> Unit,

     */
) {
    Schedule01PageTopAppBarDropdownMenu(
        iconContent = {
            Icon(
                Icons.Default.MoreVert,
                stringResource(id = R.string.help_menu)
            )
        }
    )

    { closeMenu ->

        DropdownMenuItem(
            leadingIcon = {Icon(imageVector = Icons.AutoMirrored.Filled.Help, contentDescription = null)}
            ,
            onClick = {
//                navigateToHelpSchedule01Page()
                //            WebViewSchedule01Screen("file:///android_asset/help.html");
                closeMenu()
            },
            text = { Text(text = stringResource(id = R.string.help_mainPage)) }
        )
        DropdownMenuItem(
            leadingIcon = {Icon(imageVector = Icons.Filled.AppRegistration, contentDescription = null)}
            ,
            onClick = {
//                navigateToHelpGraphicsPage()
                closeMenu()
            },
            text = { Text(text = stringResource(id = R.string.help_Graphics)) }
        )
        DropdownMenuItem(
            leadingIcon = {Icon(imageVector = Icons.Filled.LocalPolice, contentDescription = null)}
            ,
            onClick = {
//                navigateToHelpAboutPage()
                closeMenu()
            },
            text = { Text(text = stringResource(id = R.string.help_About)) }
        )
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