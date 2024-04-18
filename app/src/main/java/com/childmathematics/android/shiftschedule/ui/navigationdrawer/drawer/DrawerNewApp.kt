/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.childmathematics.android.shiftschedule.ui.navigationdrawer.drawer

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.childmathematics.android.shiftschedule.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.ui.main.MAIN_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.navigation.components.DrawerAppNavRail
import com.childmathematics.android.shiftschedule.ui.navigationdraver.components.DrawerNavigationRail
import com.childmathematics.android.shiftschedule.ui.navigationdrawer.components.DrawerNavDestinations
import com.childmathematics.android.shiftschedule.ui.navigationdrawer.components.DrawerNavigationActions
import kotlinx.coroutines.launch

@Composable
fun DrawerNewApp(
//    appContainer: com.childmathematics.android.shiftschedule.data.AppContainer,
//    appContainer: AppContainer,
    widthSizeClass: WindowWidthSizeClass,
) {
    ScheduleCalendarTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            DrawerNavigationActions(navController)
        }

        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: DrawerNavDestinations.D_MAIN_PAGE_ROUTE

        val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded

        val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)
         /*
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
                NavigationNewDrawer(
                    currentRoute = currentRoute,
                    navigateToMainPage = navigationActions.navigateToMainPage,
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
                    DrawerNavigationRail(
                        currentRoute = currentRoute,
                        navigateToMainPage = navigationActions.navigateToMainPage,
                        navigateToSchedule01 = navigationActions.navigateToSchedule01,
                        navigateToSchedule500 = navigationActions.navigateToSchedule500,
                        navigateToAbout = navigationActions.navigateToAbout,
                    )
                }
                NewDrawerNavGraph(
 //                   appContainer = appContainer,
                    isExpandedScreen = isExpandedScreen,
//                    currentRoute = currentRoute,
                    navController = navController,
                    openDrawer = { coroutineScope.launch { sizeAwareDrawerState.open() } },
                )
            }
        }
    }
}

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
