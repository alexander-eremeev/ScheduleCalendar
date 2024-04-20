package com.childmathematics.android.shiftschedule.ui.schedules.schedule01

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


internal const val SCHEDULE01_PAGE_ROUTE = "schedule01Page"
internal fun NavGraphBuilder.schedule01PageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit ,
    onOpenDrawer: Boolean ,
    /*
    navigateToHelpSchedule01Page: () -> Unit,
    navigateToHelpGraphicsPage: () -> Unit,
    navigateToHelpAboutPage: () -> Unit

     */
  ) {
    composable(route = SCHEDULE01_PAGE_ROUTE) {
        Schedule01PageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            onOpenDrawer = onOpenDrawer,openDrawer =openDrawer,
            /*
            navigateToHelpMainPage ,
            navigateToHelpGraphicsPage,
            navigateToHelpAboutPage

             */
        )
    }
}
