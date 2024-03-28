package com.childmathematics.android.shiftschedule.ui.navigationdrawer

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.childmathematics.android.shiftschedule.ui.about.navigateToAboutGraph
import com.childmathematics.android.shiftschedule.ui.main.MAIN_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.main.navigateToMainPageGraph
import com.childmathematics.android.shiftschedule.ui.navigation.DrawerDestinations
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.navigateToSchedule01PageGraph
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.navigateToSchedule500PageGraph

internal const val NAVDRAWER_ROUTE = "navDrawer"
internal fun NavGraphBuilder.navDrawerScreen(
    widthSizeClass: WindowWidthSizeClass,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navigateToMainPage: () -> Unit = {

        navController.navigate(MAIN_PAGE_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            // Всплывающее окно к начальному пункту назначения графика, чтобы
            // избегаем создания большого стека пунктов назначения
            // в заднем стеке, когда пользователи выбирают элементы
            // Перед навигацией выберите пункт назначения. При этом из заднего стека удаляются
            // все несовпадающие пункты назначения, пока этот пункт назначения не будет найден.
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            // Избегайте нескольких копий одного и того же места назначения, когда
            // повторный выбор того же элемента
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            // Восстановить состояние при повторном выборе ранее выбранного элемента
            restoreState = true
        }
    }

    composable(route = NAVDRAWER_ROUTE) {
        NavDrawerScreen(
            widthSizeClass,
            modifier,
            onBackClick = { navController.popBackStack()},
            navigateToMainPage = { navController.navigateToMainPageGraph() },
            navigateToSchedule01Page = { navController.navigateToSchedule01PageGraph() },
            navigateToSchedule500Page = { navController.navigateToSchedule500PageGraph() },
            navigateToAboutPage = { navController.navigateToAboutGraph()  }

        )
    }
}