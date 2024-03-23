package com.childmathematics.android.shiftschedule.ui.localpolices

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

internal const val LOCALPOLICES_ROUTE = "localPolices"
internal fun NavGraphBuilder.localPolicesScreen(
                navController: NavHostController,
                modifier: Modifier = Modifier,
) {
    composable(route = LOCALPOLICES_ROUTE) {
        LocalPolicesScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
        )
    }
}