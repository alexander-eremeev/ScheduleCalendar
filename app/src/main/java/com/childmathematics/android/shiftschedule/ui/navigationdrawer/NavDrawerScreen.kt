package com.childmathematics.android.shiftschedule.ui.navigationdrawer

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.childmathematics.android.shiftschedule.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.ui.about.ABOUT_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.main.MAIN_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.navigation.MainNavigationActions
import com.childmathematics.android.shiftschedule.ui.navigationdraver.components.DrawerNavigationRail
import com.childmathematics.android.shiftschedule.ui.navigationdraver.components.NavigationDrawer
import com.childmathematics.android.shiftschedule.ui.navigationdrawer.components.DrawerNavigationActions
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.SCHEDULE01_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.SCHEDULE500_PAGE_ROUTE
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
internal fun NavDrawerScreen(
    widthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    navController: NavHostController,

    onBackClick: () -> Unit,
    navigateToMainPage: () -> Unit,
    navigateToSchedule01Page: () -> Unit,
    navigateToSchedule500Page: () -> Unit,
    navigateToAboutPage: () -> Unit,
) {
    ScheduleCalendarTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        val navController = rememberNavController()
        val coroutineScope = rememberCoroutineScope()
        val navigationActions = remember(navController) {
            DrawerNavigationActions(navController)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: MAIN_PAGE_ROUTE
//        val currentRoute =  MAIN_PAGE_ROUTE

        val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded

        val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)
        /*
    modifier: Modifier = Modifier,
drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),

Material Design navigation drawer.
Navigation drawers provide ergonomic access to destinations in an app.
Modal navigation drawers block interaction with the rest of an app’s content with a scrim.
They are elevated above most of the app’s UI and don’t affect the screen’s layout grid.
Навигационный ящик Material Design.
Навигационные ящики обеспечивают эргономичный доступ к пунктам назначения в приложении.
Модальные панели навигации блокируют взаимодействие с остальным содержимым приложения
с помощью сетки. Они возвышаются над большей частью пользовательского интерфейса приложения
и не влияют на сетку макета экрана.
 */
        ModalNavigationDrawer(
            drawerContent = {
                NavigationDrawer(
                    currentRoute = currentRoute,
                    navigateToMainPage ,
                    navigateToSchedule01Page ,
                    navigateToSchedule500Page,
                    navigateToAboutPage,
                    closeDrawer = { coroutineScope.launch { sizeAwareDrawerState.close() } },
                    openDrawer = { coroutineScope.launch { sizeAwareDrawerState.open() } },

                    )
            },
            drawerState = sizeAwareDrawerState,
            // Only enable opening the drawer via gestures if the screen is not expanded
            gesturesEnabled = !isExpandedScreen
        ) {
            Row {
                if (isExpandedScreen) {
                    DrawerNavigationRail(
                        currentRoute = currentRoute,
                        navigateToMainPage ,
                        navigateToSchedule01Page,
                        navigateToSchedule500Page ,
                        navigateToAboutPage ,
                    )
                }
                /*
                if (currentRoute == MAIN_PAGE_ROUTE) navigateToMainPage()
                else if (currentRoute == SCHEDULE01_PAGE_ROUTE) navigateToSchedule01Page()
                else if (currentRoute == SCHEDULE500_PAGE_ROUTE) navigateToSchedule500Page()
                else if (currentRoute == _ABOUTPAGE_ROUTE) navigateToAboutPage()

                 */
                    /*
                                    DrawerNavGraph(
 //                   appContainer = appContainer,
 //                   isExpandedScreen = isExpandedScreen,
                    navController = navController,
                    openDrawer = { coroutineScope.launch { sizeAwareDrawerState.open() } },
                )

                     */
            }
        }
    }
}
//=================================
/**
 * Determine the drawer state to pass to the modal drawer.
 * Определите состояние ящика для передачи модальному ящику.
 */
@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        // If we want to allow showing the drawer, we use a real, remembered drawer
        // state defined above
        // Если мы хотим разрешить отображение ящика, мы используем настоящий, запомненный ящик.
        // состояние, определенное выше
        drawerState
    } else {
        // If we don't want to allow the drawer to be shown, we provide a drawer state
        // that is locked closed. This is intentionally not remembered, because we
        // don't want to keep track of any changes and always keep it closed
        // Если мы не хотим, чтобы ящик отображался, мы предоставляем состояние ящика
        // который заблокирован. Об этом намеренно не вспоминают, потому что мы
        // не хотим отслеживать какие-либо изменения и всегда держим его закрытым
        DrawerState(DrawerValue.Closed)
    }
}
/*
@Composable
fun DrawerApp(
//    appContainer: com.childmathematics.android.shiftschedule.data.AppContainer,
    widthSizeClass: WindowWidthSizeClass,
) {
    ScheduleCalendarTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            MainNavigationActions(navController)
        }

        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: MainDestinations.HOMEPAGE_ROUTE

        val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded

        val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)
        /*
            modifier: Modifier = Modifier,
        drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),

        Material Design navigation drawer.
        Navigation drawers provide ergonomic access to destinations in an app.
        Modal navigation drawers block interaction with the rest of an app’s content with a scrim.
        They are elevated above most of the app’s UI and don’t affect the screen’s layout grid.
        Навигационный ящик Material Design.
        Навигационные ящики обеспечивают эргономичный доступ к пунктам назначения в приложении.
        Модальные панели навигации блокируют взаимодействие с остальным содержимым приложения
        с помощью сетки. Они возвышаются над большей частью пользовательского интерфейса приложения
        и не влияют на сетку макета экрана.
         */
        ModalNavigationDrawer(
            drawerContent = {
                AppDrawer(
                    currentRoute = currentRoute,
                    navigateToHomePage = navigationActions.navigateToHomePage,
                    navigateToSchedule01 = navigationActions.navigateToSchedule01,
                    navigateToSchedule500 = navigationActions.navigateToSchedule500,
                    navigateToAbout = navigationActions.navigateToAbout,
                    closeDrawer = { coroutineScope.launch { sizeAwareDrawerState.close() } }
                )
            },
            drawerState = sizeAwareDrawerState,
            // Only enable opening the drawer via gestures if the screen is not expanded
            gesturesEnabled = !isExpandedScreen
        ) {
            Row {
                if (isExpandedScreen) {
                    DrawerAppNavRail(
                        currentRoute = currentRoute,
                        navigateToHomePage = navigationActions.navigateToHomePage,
                        navigateToSchedule01 = navigationActions.navigateToSchedule01,
                        navigateToSchedule500 = navigationActions.navigateToSchedule500,
                        navigateToAbout = navigationActions.navigateToAbout,
                    )
                }
                DrawerNavGraph(
 //                   appContainer = appContainer,
 //                   isExpandedScreen = isExpandedScreen,
                    navController = navController,
                    openDrawer = { coroutineScope.launch { sizeAwareDrawerState.open() } },
                )
            }
        }
    }
}
 */

