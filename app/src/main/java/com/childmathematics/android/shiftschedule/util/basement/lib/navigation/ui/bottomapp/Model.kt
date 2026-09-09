package com.childmathematics.android.basement.lib.navigation.ui.bottomapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector


object BottomMenu {

    data class MenuItem(val title: String, val icon: ImageVector)

    fun getMenuList() : List<MenuItem> {
        val menuItems = mutableListOf<MenuItem>()
        menuItems.add(MenuItem("Home", Icons.Filled.Home))
        menuItems.add(MenuItem("Search", Icons.Filled.Search))
        menuItems.add(MenuItem("Favorite", Icons.Filled.Favorite))
        menuItems.add(MenuItem("Settings", Icons.Filled.Settings))
        return menuItems
    }
    fun getMenuListschedule500() : List<MenuItem> {
        val menuItems = mutableListOf<MenuItem>()
        menuItems.add(MenuItem("Расчет для выделенных дат", Icons.Filled.Info)) // Summarize
        return menuItems
    }

}
/*

//------------------------------------------------------------------------

    val schedule500items = listOf(
        ActionItemSpec("Расчет для выделенных дат", Icons.Default.Summarize, ActionItemMode.IF_ROOM) {
            currentDialog= true
            if (currentRouteSchedule500 == RoutesSchedule500.SCHEDULE500SELSINGLE){
                currentRouteSchedule500 = RoutesSchedule500.SCHEDULE500SELPERIOD

            }
            else {
                currentRouteSchedule500 = RoutesSchedule500.SCHEDULE500SELSINGLE
            }

        },
    )



 */