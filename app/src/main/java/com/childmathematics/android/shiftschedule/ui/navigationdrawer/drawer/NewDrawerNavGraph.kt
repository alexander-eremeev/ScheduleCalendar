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

package com.childmathematics.android.shiftschedule.ui.navigationdrawer.drawer

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.presentation.Schedule01Sample
import com.childmathematics.android.shiftschedule.presentation.Schedule500Sample
import com.childmathematics.android.shiftschedule.presentation.WebViewMainScreen
import com.childmathematics.android.shiftschedule.ui.about.ABOUT_GRAPH_ROUTE
import com.childmathematics.android.shiftschedule.ui.about.aboutGraph
import com.childmathematics.android.shiftschedule.ui.about.navigateToAboutGraph

import com.childmathematics.android.shiftschedule.ui.help.HELP_GRAPH_ROUTE
import com.childmathematics.android.shiftschedule.ui.help.helpGraph

import com.childmathematics.android.shiftschedule.ui.homepage.HomePageScreen
import com.childmathematics.android.shiftschedule.ui.main.MAIN_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.main.MainPageScreen
import com.childmathematics.android.shiftschedule.ui.main.mainPageGraph
import com.childmathematics.android.shiftschedule.ui.main.mainPageScreen
import com.childmathematics.android.shiftschedule.ui.navigation.components.AppDrawer
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.SCHEDULE01_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.SCHEDULE500_PAGE_ROUTE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun NewDrawerNavGraph(
 //   appContainer: com.childmathematics.android.shiftschedule.data.AppContainer,
 //   isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    startDestination: String = MAIN_PAGE_ROUTE,
   coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
 //   navActions: MainNavigationActions = remember(navController) {
 //       MainNavigationActions(navController)}
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        aboutGraph(navController,modifier)
        mainPageGraph(navController,modifier,openDrawer)
//        menuAbout(navController)
        /*
        aboutGraph(openDrawer,navController,modifier)
        helpGraph(navController,modifier)
//        helpGraph(navController,modifier,onBackClick)

         */

        /*
           openDrawer: () -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,

         */

        composable(
            route = MAIN_PAGE_ROUTE,
//            route = DrawerDestinations.HOMEPAGE_ROUTE,
            /*
            deepLinks = listOf(
                navDeepLink {

                    uriPattern =
                        "$JETNEWS_APP_URI/${DrawerDestinations.HOMEPAGE_ROUTE}?$POST_ID={$POST_ID}"


                }
            )
             */

        ) {
            /*
            navBackStackEntry ->

            val homePageViewModel: HomePageViewModel = viewModel(
                factory = HomeViewModel.provideFactory(
                    postsRepository = appContainer.itemsRepository,
                    preSelectedPostId = navBackStackEntry.arguments?.getString(POST_ID)
                )
            )



            HomeRoute(
//                homeViewModel = homeViewModel,
                homeViewModel = homeViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer,
            )

             */
            /*
            isExpandedScreen = isExpandedScreen
            openDrawer = openDrawer
            AppModalDrawer(drawerState, currentRoute, navActions) {

             */
/*
            AppDrawer(    MainDestinations.HOMEPAGE_ROUTE,
                navigateToHomePage,
            navigateToSchedule0,
            navigateToSchedule500,
            navigateToAbout,
            closeDrawer,
            modifier
            ) {


 */
//            HomePageScreen(openDrawer = openDrawer)
//            MainPageScreen(modifier,onBackClick={},onOpenDrawer = true,openDrawer = { coroutineScope.launch { drawerState.open() } })
            mainPageScreen(navController,modifier,onOpenDrawer = true,openDrawer = { coroutineScope.launch { drawerState.open() } })
//            HomePageScreen(openDrawer = { coroutineScope.launch { drawerState.open() } })
 //           }
 //           }
        }

        composable(SCHEDULE01_PAGE_ROUTE) {
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
            Schedule01Sample(true)
        }
        composable(SCHEDULE500_PAGE_ROUTE) {
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
            Schedule500Sample(true)
        }
        composable(ABOUT_GRAPH_ROUTE) {//ABOUT_GRAPH_ROUTE
 //           composable(MainDestinations.ABOUT_ROUTE) {//ABOUT_GRAPH_ROUTE

            navController.navigateToAboutGraph()

        }
    }
}
//====================
