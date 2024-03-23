package com.childmathematics.android.shiftschedule.ui.licences

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

internal const val LICENCES_ROUTE = "licences_page"
internal fun NavGraphBuilder.licencesScreen(
                navController: NavHostController,
                modifier: Modifier = Modifier,
) {
    composable(route = LICENCES_ROUTE) {
        LicencesScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
        )
    }
}