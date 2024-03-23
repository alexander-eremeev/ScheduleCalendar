package com.childmathematics.android.shiftschedule.ui.help.mainpage

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


internal const val HELP_MAINPAGE_ROUTE = "helpMainPage"
internal fun NavGraphBuilder.helpMainPageScreen(
                navController: NavHostController,
                modifier: Modifier = Modifier,
) {
    composable(route = HELP_MAINPAGE_ROUTE) {
        HelpMainPageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
        )
    }
}