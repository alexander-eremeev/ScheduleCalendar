package com.childmathematics.android.shiftschedule.ui.help

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


internal const val HELP_PAGE_ROUTE = "helpPage"
internal fun NavGraphBuilder.helpPageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    navigateToHelpMainPage: () -> Unit,
    navigateToHelpGraphicsPage: () -> Unit,
    navigateToHelpAboutPage: () -> Unit
  ) {
    composable(route = HELP_PAGE_ROUTE) {
        HelpPageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            navigateToHelpMainPage ,
            navigateToHelpGraphicsPage,
            navigateToHelpAboutPage
        )
    }
}
