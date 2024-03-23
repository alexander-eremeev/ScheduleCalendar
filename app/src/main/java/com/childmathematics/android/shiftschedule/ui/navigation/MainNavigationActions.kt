/*
 * Copyright 2021 The Android Open Source Project
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

package com.childmathematics.android.shiftschedule.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.childmathematics.android.shiftschedule.ui.navigation.MainDestinationsArgs.ABOUT_ARG
import com.childmathematics.android.shiftschedule.ui.navigation.MainDestinationsArgs.HOMEPAGE_ARG
import com.childmathematics.android.shiftschedule.ui.navigation.MainDestinationsArgs.SCHEDULE01_ARG
import com.childmathematics.android.shiftschedule.ui.navigation.MainDestinationsArgs.SCHEDULE500_ARG
import com.childmathematics.android.shiftschedule.ui.navigation.MainScreens.ABOUT_SCREEN
import com.childmathematics.android.shiftschedule.ui.navigation.MainScreens.HOMEPAGE_SCREEN
import com.childmathematics.android.shiftschedule.ui.navigation.MainScreens.SCHEDULE01_SCREEN
import com.childmathematics.android.shiftschedule.ui.navigation.MainScreens.SCHEDULE500_SCREEN

/**
 * Screens used in [TodoDestinations]
 * Экраны, используемые в [TodoDestinations]
 */
private object MainScreens {
    const val HOMEPAGE_SCREEN = "homepage"
    const val SCHEDULE01_SCREEN = "schedule01"
    const val SCHEDULE500_SCREEN = "schedule500"
    const val ABOUT_SCREEN = "about"
}

    /**
     * Arguments used in [TodoDestinations] routes
     * Аргументы, используемые в маршрутах [TodoDestinations]
     */
    object MainDestinationsArgs {
        const val HOMEPAGE_ARG = ""
        const val SCHEDULE01_ARG = ""
        const val SCHEDULE500_ARG = ""
        const val ABOUT_ARG = ""
    }

    /**
     * Destinations used in the [TasksActivity]
     * Направления, используемые в [TasksActivity]
     */
    object MainDestinations {
        const val HOMEPAGE_ROUTE = "$HOMEPAGE_SCREEN?$HOMEPAGE_ARG"
//        const val HOMEPAGE_ROUTE = "$HOMEPAGE_SCREEN?$HOMEPAGE_ARG={$USER_MESSAGE_ARG}"
        const val SCHEDULE01_ROUTE = "$SCHEDULE01_SCREEN?$SCHEDULE01_ARG"
        const val SCHEDULE500_ROUTE = "$SCHEDULE500_SCREEN?$SCHEDULE500_ARG"
        const val ABOUT_ROUTE = "$ABOUT_SCREEN?$ABOUT_ARG"
    }

/**
 * Models the navigation actions in the app.
 */
class MainNavigationActions(navController: NavHostController) {
    val navigateToHomePage: () -> Unit = {

        navController.navigate(MainDestinations.HOMEPAGE_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            // Всплывающее окно к начальному пункту назначения графика, чтобы
            // избегаем создания большого стека пунктов назначения
            // в заднем стеке, когда пользователи выбирают элементы
            // Перед навигацией выберите пункт назначения. При этом из заднего стека удаляются
            // все несовпадающие пункты назначения, пока этот пункт назначения не будет найден.
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            // Избегайте нескольких копий одного и того же места назначения, когда
            // повторный выбор того же элемента
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            // Восстановить состояние при повторном выборе ранее выбранного элемента
            restoreState = true
        }
    }
    val navigateToSchedule01: () -> Unit = {

        navController.navigate(MainDestinations.SCHEDULE01_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToSchedule500: () -> Unit = {

        navController.navigate(MainDestinations.SCHEDULE500_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToAbout: () -> Unit = {

        navController.navigate(MainDestinations.ABOUT_ROUTE) {  //ABOUT_GRAPH_ROUTE
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }


 }
