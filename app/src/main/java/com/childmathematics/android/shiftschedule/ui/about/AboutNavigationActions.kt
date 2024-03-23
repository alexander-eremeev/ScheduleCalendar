package com.childmathematics.android.shiftschedule.ui.about

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.childmathematics.android.shiftschedule.ui.about.AboutDestinationsArgs.ABOUT_HELP_ARG
import com.childmathematics.android.shiftschedule.ui.about.AboutDestinationsArgs.ABOUT_LICENCES_ARG
import com.childmathematics.android.shiftschedule.ui.about.AboutDestinationsArgs.ABOUT_LOCALPOLICES_ARG
import com.childmathematics.android.shiftschedule.ui.about.AboutScreens.ABOUT_HELP_SCREEN
import com.childmathematics.android.shiftschedule.ui.about.AboutScreens.ABOUT_LICENCES_SCREEN
import com.childmathematics.android.shiftschedule.ui.about.AboutScreens.ABOUT_LOCALPOLICES_SCREEN

/**
 * Screens used in [TodoDestinations]
 * Экраны, используемые в [TodoDestinations]
 */
private object AboutScreens {
    const val ABOUT_HELP_SCREEN = "help"
    const val ABOUT_LICENCES_SCREEN = "licences"
    const val ABOUT_LOCALPOLICES_SCREEN = "localpolices"
}

/**
 * Arguments used in [TodoDestinations] routes
 * Аргументы, используемые в маршрутах [TodoDestinations]
 */
object AboutDestinationsArgs {
    const val ABOUT_HELP_ARG = ""
    const val ABOUT_LICENCES_ARG = ""
    const val ABOUT_LOCALPOLICES_ARG = ""

}

/**
 * Destinations used in the [TasksActivity]
 * Направления, используемые в [TasksActivity]
 */
object AboutDestinations {
    const val ABOUT_HELP_ROUTE = "$ABOUT_HELP_SCREEN?$ABOUT_HELP_ARG"
    const val ABOUT_LICENCES_ROUTE = "$ABOUT_LICENCES_SCREEN?$ABOUT_LICENCES_ARG"
    const val ABOUT_LOCALPOLICES_ROUTE = "$ABOUT_LOCALPOLICES_SCREEN?$ABOUT_LOCALPOLICES_ARG"
}

/**
 * Models the navigation actions in the app.
 */
class AboutNavigationActions(navController: NavHostController) {

    val navigateToLicences: () -> Unit = {

        navController.navigate(AboutDestinations.ABOUT_LICENCES_ROUTE) {
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
    val navigateToLocalPolices: () -> Unit = {

        navController.navigate(AboutDestinations.ABOUT_LOCALPOLICES_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToHelp: () -> Unit = {

        navController.navigate(AboutDestinations.ABOUT_HELP_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
