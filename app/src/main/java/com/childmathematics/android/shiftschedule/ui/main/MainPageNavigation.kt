package com.childmathematics.android.shiftschedule.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.shiftschedule.ui.navigationdrawer.components.DrawerNavDestinations
import kotlinx.coroutines.launch


internal const val MAIN_PAGE_ROUTE = "mainPage"

internal fun NavGraphBuilder.mainPageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onOpenDrawer: Boolean,
    openDrawer: () -> Unit
    /*
    navigateToHelpschedule500Page: () -> Unit,
    navigateToHelpGraphicsPage: () -> Unit,
    navigateToHelpAboutPage: () -> Unit

     */
  ) {
    composable(route = MAIN_PAGE_ROUTE) {
        MainPageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            onOpenDrawer = false,openDrawer ={},
        )
    }
}
