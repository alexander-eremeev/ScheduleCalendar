/*
 * Copyright 2022 The Android Open Source Project
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

package com.childmathematics.android.shiftschedule.ui.about

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.LocalPolice
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.childmathematics.android.shiftschedule.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutTopAppBar(
    onOpenDrawer: Boolean,
    openDrawer: () -> Unit,
    onBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,

    navigateToHelp: () -> Unit,
    navigateToLicences: () -> Unit,
    navigateToLocalPolices: () -> Unit,
 ) {
//fun HomePageTopAppBar(openDrawer: () -> Unit,closeDrawer: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.about_title)) },
        navigationIcon = {
            //openDrawer: () -> Unit,  -------------------------1
            if(onOpenDrawer) {
                IconButton(onClick = openDrawer) {
                    Icon(Icons.Filled.Menu, stringResource(id = R.string.back_button))
                }
            }
            else {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Filled.Menu, stringResource(id = R.string.back_button))
                }
            }
        },
        scrollBehavior = scrollBehavior,
        actions = {
           AboutMenu( navigateToHelp,
                    navigateToLicences,
                    navigateToLocalPolices,
            )
         },
        modifier = Modifier.fillMaxWidth()
//            .fillMaxHeight()
    )
//    closeDraver = closeDraver
}
/*
@Preview
@Composable
private fun AboutTopAppBarPreview() {

    ScheduleCalendarTheme {
        Surface {
            AboutTopAppBar() { }
        }
    }
}

 */
//-------------------------
@Composable
private fun AboutMenu(
    navigateToHelp: () -> Unit,
    navigateToLicences: () -> Unit,
    navigateToLocalPolices: () -> Unit,
) {
    TopAppBarDropdownMenu(
        iconContent = {
            Icon(
                Icons.Default.MoreVert,
                stringResource(id = R.string.about_menu)
            )
        }
    ) { closeMenu ->
        DropdownMenuItem(
            leadingIcon = {Icon(imageVector = Icons.AutoMirrored.Filled.Help, contentDescription = null)}
            ,
            onClick = {
                        navigateToHelp()
        //            WebViewMainScreen("file:///android_asset/help.html");
                    closeMenu()
            },
            text = { Text(text = stringResource(id = R.string.about_help)) }
        )
        DropdownMenuItem(
            leadingIcon = {Icon(imageVector = Icons.Filled.AppRegistration, contentDescription = null)}
            ,
            onClick = {
                    navigateToLicences()
                    closeMenu()
            },
            text = { Text(text = stringResource(id = R.string.about_licenses)) }
        )
        DropdownMenuItem(
            leadingIcon = {Icon(imageVector = Icons.Filled.LocalPolice, contentDescription = null)}
            ,
            onClick = {
                navigateToLocalPolices()
                closeMenu()
            },
            text = { Text(text = stringResource(id = R.string.about_localpolices)) }
        )
    }
}
//-----------------------------
@Composable
private fun TopAppBarDropdownMenu(
    iconContent: @Composable () -> Unit,
    content: @Composable ColumnScope.(() -> Unit) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
        IconButton(onClick = { expanded = !expanded }) {
            iconContent()
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentSize(Alignment.TopEnd)
        ) {
            content { expanded = !expanded }
        }
    }
}
