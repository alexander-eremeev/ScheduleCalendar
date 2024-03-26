package com.childmathematics.android.shiftschedule.ui.navigationdrawer

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

internal const val NAVDRAWER_ROUTE = "navDrawer"
internal fun NavGraphBuilder.navDrawerScreen(
                navController: NavHostController,
                modifier: Modifier = Modifier,
) {
    composable(route = NAVDRAWER_ROUTE) {
        NavDrawerScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
        )
    }
}