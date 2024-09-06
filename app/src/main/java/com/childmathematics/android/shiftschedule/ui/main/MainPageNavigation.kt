package com.childmathematics.android.shiftschedule.ui.main

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.shiftschedule.ui.in_app_update.UpdateViewModel


internal const val MAIN_PAGE_ROUTE = "mainPage"

internal fun NavGraphBuilder.mainPageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onOpenDrawer: Boolean,
    openDrawer: () -> Unit,
    updateViewModel: UpdateViewModel
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
            onOpenDrawer = onOpenDrawer,
            openDrawer =openDrawer,
            updateViewModel
        )
    }
}
