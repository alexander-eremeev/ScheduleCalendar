package com.childmathematics.android.shiftschedule.ui.about

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeIn
import com.childmathematics.android.basement.lib.navigation.ui.screenFadeOut
import com.childmathematics.android.shiftschedule.ui.help.helpGraph
import com.childmathematics.android.shiftschedule.ui.help.navigateToHelpGraph
import com.childmathematics.android.shiftschedule.ui.in_app_update.UpdateViewModel
import com.childmathematics.android.shiftschedule.ui.in_app_update.inAppUpdatePageGraph
import com.childmathematics.android.shiftschedule.ui.in_app_update.navigateToInAppUpdatePageGraph
import com.childmathematics.android.shiftschedule.ui.licences.licencesGraph
import com.childmathematics.android.shiftschedule.ui.licences.navigateToLicencesGraph
import com.childmathematics.android.shiftschedule.ui.localpolices.localPolicesGraph
import com.childmathematics.android.shiftschedule.ui.localpolices.navigateToLocalPolicesGraph

internal const val ABOUT_GRAPH_ROUTE = "about_graph"
private const val ABOUT_DEEPLINK ="$ROOT_DEEPLINK/open_source_licenses.html"
//"$ROOT_DEEPLINK/about.html?Version=$BuildConfig.VERSION_NAME&Year=$BuildConfig.BUILD_TIMESTAMP"

fun NavController.navigateToAboutGraph() {
    navigate(ABOUT_GRAPH_ROUTE)
}
//====================
fun NavGraphBuilder.aboutGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit ,
    onOpenDrawer: Boolean,
    updateViewModel: UpdateViewModel

) {
    // =====================================
    helpGraph(navController  = navController)
    localPolicesGraph(navController  = navController)
    licencesGraph(navController  = navController)
    inAppUpdatePageGraph(navController  = navController,
        updateViewModel=updateViewModel)
    // =====================================

    // =====================================
    navigation(startDestination = ABOUT_PAGE_ROUTE,
        route = ABOUT_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = ABOUT_DEEPLINK }
        ),
        enterTransition = { screenFadeIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenFadeOut() },
    ) {
            aboutPageScreen(
                navController,
                modifier,
                openDrawer,
                onOpenDrawer,
                navigateToHelp = {navController.navigateToHelpGraph()},
                navigateToLicences = {navController.navigateToLicencesGraph()},
                navigateToLocalPolices={ navController.navigateToLocalPolicesGraph() },
                navigateToAppUpdate={ navController.navigateToInAppUpdatePageGraph() }
            )
    }
}
