package com.childmathematics.android.shiftschedule.presentation.ui.countries

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.childmathematics.android.basement.lib.navigation.ui.ROOT_DEEPLINK
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.NonWorkingDaysViewModel
import com.childmathematics.android.shiftschedule.presentation.ui.nonworkingdays.year.nonWorkingDaysYearPageGraph

const val COUNTRIES_GRAPH_ROUTE = "countries_graph"
private const val COUNTRIES_DEEPLINK ="$ROOT_DEEPLINK/countries.html"
/*
Один из них является расширением NavController.
Это позволяет нам перейти к данному экрану.
 */
fun NavController.navigateToCountriesPageGraph() {
    navigate(COUNTRIES_GRAPH_ROUTE)
}
//=============================
//=============================
/*
Второй расширяет NavGraphBuilder. Мы используем его, чтобы включить
выбранный экран в качестве пункта назначения в NavHost.
 */
fun NavGraphBuilder.countriesPageGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    onOpenDrawer: Boolean,
    countriesViewModel: CountriesViewModel,
) {
    // =====================================
//        nonworkingDays01SummingPageGraph(navController)
//    countriesYearPageGraph(navController,countriesViewModel = countriesViewModel)

    // =====================================
/*
// если нет вложенных меню то не работает
// протестировать правильность использования !!!!

    navigation(startDestination = COUNTRIES_PAGE_ROUTE,
        route = COUNTRIES_GRAPH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = COUNTRIES_DEEPLINK }
        ),

        enterTransition = { screenFadeIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenFadeOut() },

        ){
        composable(route = COUNTRIES_PAGE_ROUTE) {
            countriesPageScreen(
                navController,
                modifier,
                onOpenDrawer = onOpenDrawer, openDrawer = openDrawer,
                countriesViewModel = countriesViewModel
            )
        }
    }
    */
}
