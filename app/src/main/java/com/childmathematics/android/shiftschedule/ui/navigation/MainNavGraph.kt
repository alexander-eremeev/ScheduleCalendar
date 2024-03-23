/*
 * Copyright (C) 2023 The Android Open Source Project
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

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.childmathematics.android.shiftschedule.data.AppContainer
//import com.childmathematics.android.shiftschedule.ui.about.AboutDestination
import com.childmathematics.android.shiftschedule.ui.home.HomeDestination
import com.childmathematics.android.shiftschedule.ui.home.HomeScreen
import com.childmathematics.android.shiftschedule.ui.home.HomeViewModel
import com.childmathematics.android.shiftschedule.ui.homepage.HomePageScreen
import com.childmathematics.android.shiftschedule.ui.homepage.HomePageViewModel
import com.childmathematics.android.shiftschedule.ui.item.ItemDetailsDestination
import com.childmathematics.android.shiftschedule.ui.item.ItemDetailsScreen
import com.childmathematics.android.shiftschedule.ui.item.ItemEditDestination
import com.childmathematics.android.shiftschedule.ui.item.ItemEditScreen
import com.childmathematics.android.shiftschedule.ui.item.ItemEntryDestination
import com.childmathematics.android.shiftschedule.ui.item.ItemEntryScreen
import com.childmathematics.android.shiftschedule.ui.schedule01.Schedule01Destination
import com.childmathematics.android.shiftschedule.ui.schedule01.Schedule01Screen
import com.childmathematics.android.shiftschedule.ui.schedule01.Schedule01ViewModel
import com.childmathematics.android.shiftschedule.ui.schedule500.Schedule500Destination
import com.childmathematics.android.shiftschedule.ui.schedule500.Schedule500Screen


/**
 * Provides Navigation graph for the application.
 * Предоставляет график навигации для приложения.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    /*
    NavHost(
        navController = navController,
//        startDestination = AboutDestination.route,
        startDestination = Schedule500Destination.route,
//        startDestination = HomePageDestination.route,
//        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomePageDestination.route) {
            HomePageScreen(
 //               navigateToHomePage = {
//                    navController.navigate(ItemEntryDestination.route)
                },
 //               navigateToItemUpdate = {
//                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = Schedule01Destination.route) {
            Schedule01Screen(
                navigateToItemEntry = {
//                    navController.navigate(ItemEntryDestination.route)
                },
                navigateToItemUpdate = {
//                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = Schedule500Destination.route) {
            Schedule500Screen(
                navigateToItemEntry = {
//                    navController.navigate(ItemEntryDestination.route)
                },
                navigateToItemUpdate = {
//                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = AboutDestination.route) {
            AboutScreen(
                navigateToItemEntry = {
//;                    navController.navigate(ItemEntryDestination.route)
                    },

                navigateToItemUpdate = {
 //                   navController.navigate("${ItemDetailsDestination.route}/${it}")
                }
            )
        }
//===============================================
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(ItemEntryDestination.route) },
                navigateToItemUpdate = {
                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = ItemEntryDestination.route) {
            ItemEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemDetailsScreen(
                navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }

     */
}

// ----------------------------------------------------------------------------
/*
@Composable
fun MainNewAppNavGraph(
    appContainer: AppContainer,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
//    startDestination: String = MainDestinations.HOME_ROUTE,
    startDestination: String = HomePageDestination.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = HomePageDestination.route,
//            route = JetnewsDestinations.HOME_ROUTE,
            /*
            deepLinks = listOf(
                navDeepLink {
                    uriPattern =
                        "$JETNEWS_APP_URI/${JetnewsDestinations.HOME_ROUTE}?$POST_ID={$POST_ID}"
                }
            )

             */
        ) { navBackStackEntry ->
            val homePageViewModel: HomePageViewModel = viewModel(
                /*
                factory = HomePageViewModel.provideFactory(
                    itemRepository = appContainer.ItemRepository,
 //                   preSelectedPostId = navBackStackEntry.arguments?.getString(POST_ID)
                )

                 */
            )
            HomePageScreen(
//            HomeRoute(
                homeViewModel = homeViewModel,
//                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer,
            )
        }
        composable(Schedule01Destination.route) {
//        composable(JetnewsDestinations.INTERESTS_ROUTE) {
            val schedule01ViewModel: Schedule01ViewModel = viewModel(
                factory = Schedule01ViewModel.provideFactory(appContainer.interestsRepository)
            )
            Schedule01Route(
                interestsViewModel = schedule01ViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
    }
}
//=============================================================================

 */


