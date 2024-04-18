/*
 * Copyright 2020 The Android Open Source Project
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

package com.childmathematics.android.shiftschedule.ui.navigationdrawer.drawer

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.ui.main.MAIN_PAGE_ROUTE
import com.childmathematics.android.shiftschedule.ui.navigation.DrawerDestinations
import com.childmathematics.android.shiftschedule.ui.navigationdrawer.components.DrawerNavDestinations


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationNewDrawer(
    currentRoute: String,
    navigateToMainPage: () -> Unit,
    navigateToSchedule01: () -> Unit,
    navigateToSchedule500: () -> Unit,
    navigateToAbout: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Content inside of a modal navigation drawer.
    ModalDrawerSheet(modifier
        .verticalScroll(rememberScrollState())
    ) {
        DrawerHeader(
            modifier = Modifier
//                .padding(horizontal = 28.dp, vertical = 24.dp)
                .padding(horizontal = 3.dp, vertical = 3.dp)
        )
        /*
        Material Design navigation drawer item.
        A NavigationDrawerItem represents a destination within drawers, either ModalNavigationDrawer,
        PermanentNavigationDrawer or DismissibleNavigationDrawer.
        Элемент навигационного ящика Material Design.
        NavigationDrawerItem представляет пункт назначения в ящиках: ModalNavigationDrawer,
        PermanentNavigationDrawer или DismissibleNavigationDrawer.
         */
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.homepage_title)) },
            icon = { Icon(painterResource(R.drawable.home_24px), null) },
            selected = currentRoute == DrawerNavDestinations.D_MAIN_PAGE_ROUTE,
            onClick = {
                navigateToMainPage(); closeDrawer()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        HorizontalDivider()
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.schedule01_title)) },
            icon = { Icon(painterResource(R.drawable.shift_8), null) },
            selected = currentRoute == DrawerNavDestinations.D_SCHEDULE01_PAGE_ROUTE,
            onClick = {
                navigateToSchedule01(); closeDrawer()
            },
            modifier = Modifier
                .padding(NavigationDrawerItemDefaults.ItemPadding)
                .height(96.dp)
        )
        HorizontalDivider()
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.schedule500_title)) },
            icon = { Icon(painterResource(R.drawable.shift_12), null) },
            selected = currentRoute == DrawerNavDestinations.D_SCHEDULE500_PAGE_ROUTE,
            onClick = {
                navigateToSchedule500();  closeDrawer()
            },
            modifier = Modifier
                .padding(NavigationDrawerItemDefaults.ItemPadding)
                .height(96.dp)
        )
        HorizontalDivider()
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.about_title)) },
            icon = { Icon(painterResource(R.drawable.info_outl_24px), null) },
            selected = currentRoute == DrawerNavDestinations.D_ABOUT_PAGE_ROUTE,
            onClick = {
                navigateToAbout(); closeDrawer()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}

@Composable
private fun DrawerHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .border(BorderStroke(3.dp, MaterialTheme.colorScheme.onSurface)),
        contentAlignment = Alignment.BottomStart
    ) {
        Icon(
            modifier = Modifier
                .padding(80.dp, 0.dp, 7.dp, 50.dp)
                .height(150.dp),
            painter = painterResource(id = R.drawable.powering_idea_4),
            contentDescription = null,
            tint = Color.Unspecified
        )

        Column(modifier = Modifier.padding(5.dp)) {
            Spacer(Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_shiftschedule),
                contentDescription = stringResource(R.string.app_name),
                tint = Color.Unspecified
            )
            Text(stringResource(id =R.string.drawer_header))
            Text(stringResource(id =R.string.e_mail), fontSize = 10.sp)
        }
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    ScheduleCalendarTheme {
        NavigationNewDrawer(
            currentRoute = MAIN_PAGE_ROUTE,
            navigateToMainPage = {},
            navigateToSchedule01 = {},
            navigateToSchedule500 = {},
            navigateToAbout = {},
            closeDrawer = { }
        )
    }
}
