package com.childmathematics.android.shiftschedule.ui.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.presentation.WebViewMainScreen
import com.childmathematics.android.shiftschedule.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.ui.AppViewModelProvider

@OptIn(ExperimentalMaterial3Api::class)

@Composable
internal fun AboutPageScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    onOpenDrawer: Boolean,
    openDrawer: () -> Unit,
    navigateToHelp: () -> Unit,
    navigateToLicences: () -> Unit,
    navigateToLocalPolices: () -> Unit,

    viewModel: AboutViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    ScheduleCalendarTheme {
        /*
        Собирает значения из этого StateFlow и представляет его последнее значение через State. StateFlow.value
        используется в качестве начального значения. Каждый раз, когда в StateFlow будет отправляться новое значение,
        возвращаемое состояние будет обновляться, вызывая рекомпозицию каждого использования State.value.
         */
        val aboutUiState by viewModel.aboutUiState.collectAsState()
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
        val topAppBarState = rememberTopAppBarState()
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState, { true },)
//        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            modifier = modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar =
            {
                AboutTopAppBar(
                    onOpenDrawer  = onOpenDrawer,
                    openDrawer = openDrawer,
                    onBackClick,
                    scrollBehavior,
                    navigateToHelp,
                    navigateToLicences,
                    navigateToLocalPolices,
                    )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    val yearStr = BuildConfig.BUILD_TIMESTAMP
                    val versionName = BuildConfig.VERSION_NAME

                    WebViewMainScreen("file:///android_asset/about.html?Version=$versionName&Year=$yearStr")
                }
            },
        )
    }
}
