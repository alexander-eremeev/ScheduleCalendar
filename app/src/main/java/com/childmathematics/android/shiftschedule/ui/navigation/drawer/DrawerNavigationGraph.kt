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

package com.childmathematics.android.shiftschedule.ui.navigation.drawer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.childmathematics.android.shiftschedule.ui.about.AboutPageScreen
import com.childmathematics.android.shiftschedule.ui.about.aboutGraph

import com.childmathematics.android.shiftschedule.ui.main.MainPageScreen
import com.childmathematics.android.shiftschedule.ui.main.mainPageGraph
import com.childmathematics.android.shiftschedule.ui.navigation.drawer.components.DrawerNavDestinations
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.Schedule01PageScreen
import com.childmathematics.android.shiftschedule.ui.ScheduleViewModel
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.schedule01PageGraph
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.summingpage.navigateToSchedule01SummingPageGraph
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.Schedule500PageScreen
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.schedule500PageGraph
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.summingpage.navigateToSchedule500SummingPageGraph
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun DrawerNavigationGraph(
 //   appContainer: com.childmathematics.android.shiftschedule.data.AppContainer,
///    appContainer: AppContainer,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onOpenDrawer: Boolean,
    openDrawer: () -> Unit,
    startDestination: String = DrawerNavDestinations.D_MAIN_PAGE_ROUTE,
    scheduleViewModel: ScheduleViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        mainPageGraph(navController,modifier,openDrawer ,onOpenDrawer = onOpenDrawer )
        schedule01PageGraph(navController,modifier,openDrawer,onOpenDrawer = onOpenDrawer ,scheduleViewModel = scheduleViewModel)
        schedule500PageGraph(navController,modifier,openDrawer,onOpenDrawer = onOpenDrawer,scheduleViewModel = scheduleViewModel)
        aboutGraph(navController,modifier,openDrawer,onOpenDrawer = onOpenDrawer )

        composable(
            route = DrawerNavDestinations.D_MAIN_PAGE_ROUTE,
            ) {
                MainPageScreen(modifier,onBackClick={},
                onOpenDrawer = onOpenDrawer,openDrawer = openDrawer)
            }

        composable(
            route = DrawerNavDestinations.D_SCHEDULE01_PAGE_ROUTE,) {

            Schedule01PageScreen(modifier,onBackClick={navController.popBackStack()},
                onOpenDrawer = onOpenDrawer,openDrawer = openDrawer,
                navigateToSchedule01SummingPage = {navController.navigateToSchedule01SummingPageGraph()},
                scheduleViewModel = scheduleViewModel
            )
        }
        composable(
            route = DrawerNavDestinations.D_SCHEDULE500_PAGE_ROUTE) {
            /*
                val interestsViewModel: InterestsViewModel = viewModel(
                    factory = InterestsViewModel.provideFactory(appContainer.itemsRepository)
                )
                InterestsRoute(
                    interestsViewModel = interestsViewModel,
                    isExpandedScreen = isExpandedScreen,
                    openDrawer = openDrawer
                )
             */

                Schedule500PageScreen(modifier,onBackClick={},
                    onOpenDrawer = onOpenDrawer,openDrawer = openDrawer,
                    navigateToSchedule500SummingPage = {navController.navigateToSchedule500SummingPageGraph()},
                    scheduleViewModel = scheduleViewModel
                    )

        }
        composable(
            route = DrawerNavDestinations.D_ABOUT_PAGE_ROUTE) {
                AboutPageScreen(onBackClick={},modifier,onOpenDrawer = true,
                    openDrawer = openDrawer,navigateToHelp={},
                    navigateToLicences={},navigateToLocalPolices={},)
        }
    }
}
