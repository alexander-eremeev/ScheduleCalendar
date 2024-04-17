package com.childmathematics.android.shiftschedule.ui.schedules.schedule500

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


internal const val SCHEDULE500_PAGE_ROUTE = "schedule500Page"
internal fun NavGraphBuilder.schedule500PageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    /*
    navigateToHelpSchedule500Page: () -> Unit,
    navigateToHelpGraphicsPage: () -> Unit,
    navigateToHelpAboutPage: () -> Unit

     */
  ) {
    composable(route = SCHEDULE500_PAGE_ROUTE) {
        Schedule500PageScreen(
            modifier,
            onBackClick = { navController.popBackStack()},
            onOpenDrawer = false,openDrawer ={},

            /*
            navigateToHelpMainPage ,
            navigateToHelpGraphicsPage,
            navigateToHelpAboutPage

             */
        )
    }
}
