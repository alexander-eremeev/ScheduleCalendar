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

package com.childmathematics.android.shiftschedule.ui.navigationdrawer.components

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.childmathematics.android.shiftschedule.ui.about.ABOUT_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.main.MAIN_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.SCHEDULE01_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.SCHEDULE500_PAGE_ROUTE

object DrawerNavDestinations {
    const val D_MAIN_PAGE_ROUTE = MAIN_PAGE_ROUTE

    //        const val HOMEPAGE_ROUTE = "$HOMEPAGE_SCREEN?$HOMEPAGE_ARG={$USER_MESSAGE_ARG}"
    const val D_SCHEDULE01_PAGE_ROUTE = SCHEDULE01_PAGE_ROUTE
    const val D_SCHEDULE500_PAGE_ROUTE = SCHEDULE500_PAGE_ROUTE
    const val D_ABOUT_PAGE_ROUTE = ABOUT_PAGE_ROUTE
}
/**
 * Models the navigation actions in the app.
 */
class DrawerNavigationActions(navController: NavHostController) {
    val navigateToMainPage: () -> Unit = {

        navController.navigate(DrawerNavDestinations.D_MAIN_PAGE_ROUTE) {
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

        navController.navigate(DrawerNavDestinations.D_SCHEDULE01_PAGE_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToSchedule500: () -> Unit = {

        navController.navigate(DrawerNavDestinations.D_SCHEDULE500_PAGE_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToAbout: () -> Unit = {

        navController.navigate(DrawerNavDestinations.D_ABOUT_PAGE_ROUTE) {  //ABOUT_GRAPH_ROUTE
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
