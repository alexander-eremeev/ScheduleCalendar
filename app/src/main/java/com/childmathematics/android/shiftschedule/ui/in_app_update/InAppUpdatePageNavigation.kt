package com.childmathematics.android.shiftschedule.ui.in_app_update

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


internal const val IN_APP_UPDATEPAGE_ROUTE = "in_App_UpdatePage"
internal fun NavGraphBuilder.inAppUpdatePageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    updateViewModel: UpdateViewModel,
) {
    composable(route = IN_APP_UPDATEPAGE_ROUTE) {
        InAppUpdatePageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            updateViewModel
        )
    }
}