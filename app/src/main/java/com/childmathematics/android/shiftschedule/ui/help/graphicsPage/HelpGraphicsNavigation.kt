package com.childmathematics.android.shiftschedule.ui.help.Graphics

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.shiftschedule.ui.help.Graphics.HelpGraphicsScreen


internal const val HELP_GRAPHICS_ROUTE = "helpGraphics"
internal fun NavGraphBuilder.helpGraphicsScreen(
                navController: NavHostController,
                modifier: Modifier = Modifier,
//                navigateToHelpGraphics: () -> Unit,

) {
    composable(route = HELP_GRAPHICS_ROUTE) {
        HelpGraphicsScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
        )
    }
}