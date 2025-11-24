package com.childmathematics.android.shiftschedule.presentation.ui.countries

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

internal const val COUNTRIES_PAGE_ROUTE = "countriesPage"
internal fun NavGraphBuilder.countriesPageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    onOpenDrawer: Boolean,
    countriesViewModel : CountriesViewModel,
) {
    composable(route = COUNTRIES_PAGE_ROUTE) {

        CountriesPageScreen(
            modifier,
            onBackClick = { navController.popBackStack() },
            onOpenDrawer = onOpenDrawer,
            openDrawer = openDrawer,
 //           navigateToCountriesYearPage = { navController.navigateToCountriesYearPageGraph() },
            countriesViewModel,
        )
    }
}