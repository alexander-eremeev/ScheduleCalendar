/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.childmathematics.android.shiftschedule.ui.navigationdraver.components

//import com.childmathematics.android.shiftschedule.jetnews.ui.theme.JetnewsTheme
import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.ui.about.ABOUT_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.main.MAIN_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.SCHEDULE01_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.SCHEDULE500_PAGE_ROUTE

@Composable
fun DrawerNavigationRail(
    currentRoute: String,
    navigateToMainPage: () -> Unit,
    navigateToSchedule01 : () -> Unit,
    navigateToSchedule500: () -> Unit,
    navigateToAbout: () -> Unit,
    modifier: Modifier = Modifier
) {
    /*
    Material Design bottom navigation rail.
    Navigation rails provide access to primary destinations in apps when using tablet and desktop
    screens.!Navigation rail image
    The navigation rail should be used to display three to seven app destinations and, optionally,
     a FloatingActionButton or a logo header. Each destination is typically represented by
     an icon and an optional text label.
    NavigationRail should contain multiple NavigationRailItems, each representing a singular destination.
    Нижняя направляющая Material Design.
    Навигационные направляющие обеспечивают доступ к основным пунктам назначения в приложениях
     при использовании экранов планшетов и настольных компьютеров.
    !Изображение навигационной железной дороги
    Навигационную панель следует использовать для отображения от трех до семи пунктов назначения
    приложений и, при необходимости, FloatingActionButton или заголовка логотипа. Каждый пункт
     назначения обычно обозначается значком и дополнительной текстовой меткой.
    NavigationRail должен содержать несколько элементов NavigationRailItem, каждый из которых
    представляет отдельный пункт назначения.
     */
    NavigationRail(
        header = {
            Icon(
                painterResource(R.drawable.ic_shiftschedule),
                null,
                Modifier.padding(vertical = 12.dp),
                tint = Color.Unspecified
            )
        },
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier
            .weight(1f)
        )
        NavigationRailItem(
            selected = currentRoute == MAIN_PAGE_ROUTE,
            onClick = navigateToMainPage,
            icon = { Icon(painterResource(R.drawable.home_24px), stringResource(R.string.homepage_title)) },
//            icon = { Icon(Icons.Filled.Home, stringResource(R.string.homepage_title)) },
            label = { Text(stringResource(R.string.homepage_title)) },
            alwaysShowLabel = false
        )
        NavigationRailItem(
            selected = currentRoute == SCHEDULE01_PAGE_ROUTE,
            onClick = navigateToSchedule01,
            icon = { Icon(painterResource(R.drawable.shift_8), stringResource(R.string.schedule01_titleshort)) },
//              icon = { Icon(painterResource(R.drawable.sharp_counter_8_24), stringResource(R.string.schedule01_titleshort)) },
//            icon = { Icon(Icons.Filled.ListAlt, stringResource(R.string.schedule01_titleshort)) },
            label = { Text(stringResource(R.string.schedule01_titleshort)) },
            alwaysShowLabel = false
        )
        NavigationRailItem(
            selected = currentRoute == SCHEDULE500_PAGE_ROUTE,
            onClick = navigateToSchedule500,
            icon = { Icon(painterResource(R.drawable.shift_12), stringResource(R.string.schedule500_titleshort)) },
//              icon = { Icon(painterResource(R.drawable.sharp_counter_8_24), stringResource(R.string.schedule01_titleshort)) },
//            icon = { Icon(Icons.Filled.ListAlt, stringResource(R.string.schedule01_titleshort)) },
            label = { Text(stringResource(R.string.schedule500_titleshort)) },
            alwaysShowLabel = false
        )
        NavigationRailItem(
            selected = currentRoute == ABOUT_PAGE_ROUTE,
            onClick = navigateToAbout,
            icon = { Icon(painterResource(R.drawable.info_outl_24px), stringResource(R.string.about_title)) },
//              icon = { Icon(painterResource(R.drawable.sharp_counter_8_24), stringResource(R.string.schedule01_titleshort)) },
//            icon = { Icon(Icons.Filled.ListAlt, stringResource(R.string.schedule01_titleshort)) },
            label = { Text(stringResource(R.string.about_title)) },
            alwaysShowLabel = false
        )
        Spacer(Modifier.weight(1f))
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppNavRail() {
    ScheduleCalendarTheme {
        DrawerNavigationRail(
            currentRoute = "",
            navigateToMainPage = { /*TODO*/ },
            navigateToSchedule01 = { /*TODO*/ },
            navigateToSchedule500 = { /*TODO*/ },
            navigateToAbout = { /*TODO*/ })
    }
}
