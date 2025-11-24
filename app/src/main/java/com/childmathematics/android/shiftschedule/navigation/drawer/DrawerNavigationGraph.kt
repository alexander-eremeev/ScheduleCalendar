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

package com.childmathematics.android.shiftschedule.navigation.drawer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.childmathematics.android.shiftschedule.presentation.ui.ScheduleViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.about.AboutPageScreen
import com.childmathematics.android.shiftschedule.presentation.ui.about.aboutGraph
import com.childmathematics.android.shiftschedule.presentation.ui.inappupdate.UpdateViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.main.MainPageScreen
import com.childmathematics.android.shiftschedule.presentation.ui.main.mainPageGraph
import com.childmathematics.android.shiftschedule.navigation.drawer.components.DrawerNavDestinations
import com.childmathematics.android.shiftschedule.presentation.ui.countries.CountriesPageScreen
import com.childmathematics.android.shiftschedule.presentation.ui.countries.CountriesViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.countries.countriesPageGraph
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.NonWorkingDaysPageScreen
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.NonWorkingDaysViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.nonWorkingDaysPageGraph
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.year.navigateToNonWorkingDaysYearPageGraph
import com.childmathematics.android.shiftschedule.presentation.ui.schedules.schedule01.Schedule01PageScreen
import com.childmathematics.android.shiftschedule.presentation.ui.schedules.schedule01.schedule01PageGraph
import com.childmathematics.android.shiftschedule.presentation.ui.schedules.schedule01.summingpage.navigateToSchedule01SummingPageGraph
import com.childmathematics.android.shiftschedule.presentation.ui.schedules.schedule500.Schedule500PageScreen
import com.childmathematics.android.shiftschedule.presentation.ui.schedules.schedule500.schedule500PageGraph
import com.childmathematics.android.shiftschedule.presentation.ui.schedules.schedule500.summingpage.navigateToSchedule500SummingPageGraph
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
    countriesViewModel: CountriesViewModel = viewModel(),

    nonWorkingDaysViewModel: NonWorkingDaysViewModel= viewModel(),
    scheduleViewModel: ScheduleViewModel = viewModel(),
    updateViewModel: UpdateViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        updateViewModel.CheckForUpdatedApp()

        mainPageGraph(navController,modifier,openDrawer ,onOpenDrawer = onOpenDrawer,
                updateViewModel = updateViewModel )

        countriesPageGraph(navController,modifier,openDrawer,onOpenDrawer = onOpenDrawer ,
            countriesViewModel = countriesViewModel)

        nonWorkingDaysPageGraph(navController,modifier,openDrawer,onOpenDrawer = onOpenDrawer ,
            nonWorkingDaysViewModel = nonWorkingDaysViewModel)

        schedule01PageGraph(navController,modifier,openDrawer,onOpenDrawer = onOpenDrawer ,
            scheduleViewModel = scheduleViewModel)
        schedule500PageGraph(navController,modifier,openDrawer,onOpenDrawer = onOpenDrawer,
            scheduleViewModel = scheduleViewModel)
        aboutGraph(navController,modifier,openDrawer,onOpenDrawer = onOpenDrawer,
                updateViewModel = updateViewModel )

        composable(route = DrawerNavDestinations.D_MAIN_PAGE_ROUTE,) {
 //           updateViewModel.CheckForUpdateApp()
            MainPageScreen(modifier,onBackClick={},
                onOpenDrawer = onOpenDrawer,openDrawer = openDrawer,
                updateViewModel = updateViewModel
            )
        }
        composable(route = DrawerNavDestinations.D_COUNTRIES_PAGE_ROUTE,) {
            CountriesPageScreen(modifier,onBackClick={},
                onOpenDrawer = onOpenDrawer,
                openDrawer = openDrawer,
//                navigateToCountries = {navController.navigateToCountries()},
                countriesViewModel = countriesViewModel
            )
        }

        composable(route = DrawerNavDestinations.D_NONWORKINGDAYS_PAGE_ROUTE,) {
            NonWorkingDaysPageScreen(modifier,onBackClick={},
                onOpenDrawer = onOpenDrawer,openDrawer = openDrawer,
                navigateToNonWorkingDaysYearPage = {navController.navigateToNonWorkingDaysYearPageGraph()},
                nonWorkingDaysViewModel = nonWorkingDaysViewModel
            )
        }



        composable(route = DrawerNavDestinations.D_SCHEDULE01_PAGE_ROUTE,) {
            Schedule01PageScreen(modifier,onBackClick={},
                onOpenDrawer = onOpenDrawer,openDrawer = openDrawer,
                navigateToSchedule01SummingPage = {navController.navigateToSchedule01SummingPageGraph()},
                scheduleViewModel = scheduleViewModel
            )
        }

        composable(route = DrawerNavDestinations.D_SCHEDULE500_PAGE_ROUTE) {

                Schedule500PageScreen(modifier,onBackClick={},
                    onOpenDrawer = onOpenDrawer,openDrawer = openDrawer,
                    navigateToSchedule500SummingPage = {navController.navigateToSchedule500SummingPageGraph()},
                    scheduleViewModel = scheduleViewModel
                )
        }

        composable(route = DrawerNavDestinations.D_ABOUT_PAGE_ROUTE) {

            AboutPageScreen(onBackClick={},modifier,onOpenDrawer = true,
                    openDrawer = openDrawer,navigateToHelp={},
                    navigateToLicences={},navigateToLocalPolices={},navigateToAppUpdate={},

                    updateViewModel = updateViewModel
            )
        }
    }
}
