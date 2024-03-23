package com.childmathematics.android.shiftschedule.ui.help.aboutpage

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

internal const val HELP_ABOUT_ROUTE = "helpAbout"
internal fun NavGraphBuilder.helpAboutScreen(
                navController: NavHostController,
                modifier: Modifier = Modifier,
) {
    composable(route = HELP_ABOUT_ROUTE) {
        HelpAboutScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
        )
    }
}