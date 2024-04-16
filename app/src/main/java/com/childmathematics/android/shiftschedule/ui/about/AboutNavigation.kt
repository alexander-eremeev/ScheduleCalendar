package com.childmathematics.android.shiftschedule.ui.about

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val ABOUT_PAGE_ROUTE = "aboutPage"

internal fun NavGraphBuilder.aboutPageScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    navigateToHelp: () -> Unit,
    navigateToLicences: () -> Unit,
    navigateToLocalPolices: () -> Unit,
)
{
    composable(route = ABOUT_PAGE_ROUTE) {
        AboutPageScreen(
            onBackClick = { navController.popBackStack()},
//            navController,
            modifier,
//            onBackClick,
            navigateToHelp = navigateToHelp,
            navigateToLicences = navigateToLicences,
            navigateToLocalPolices =navigateToLocalPolices

         )
    }

}
