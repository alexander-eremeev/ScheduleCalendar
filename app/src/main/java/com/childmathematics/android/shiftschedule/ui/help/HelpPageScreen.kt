package com.childmathematics.android.shiftschedule.ui.help

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.LocalPolice
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.presentation.WebViewMainScreen
import com.childmathematics.android.shiftschedule.theme.ScheduleCalendarTheme
import com.childmathematics.android.shiftschedule.ui.about.AboutNavigationActions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HelpPageScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    navigateToHelpMainPage: () -> Unit,
    navigateToHelpGraphicsPage: () -> Unit,
    navigateToHelpAboutPage: () -> Unit
) {
    ScheduleCalendarTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        Scaffold(
            modifier = modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                HelpTopAppBar(
                    onBackClick,scrollBehavior,
                    navigateToHelpMainPage,
                    navigateToHelpGraphicsPage,
                    navigateToHelpAboutPage
                )
            },
            content ={ padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    WebViewMainScreen("file:///android_asset/help.html")
                  }
            },
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun HelpTopAppBar(
                          onBackClick: () -> Unit,
                          scrollBehavior: TopAppBarScrollBehavior,
                          navigateToHelpMainPage: () -> Unit,
                          navigateToHelpGraphicsPage: () -> Unit,
                          navigateToHelpAboutPage: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.about_help))
        },
        navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.Menu, stringResource(id = R.string.back_button))
                    }
        },
        scrollBehavior = scrollBehavior,
        actions = {
            HelpMenu( navigateToHelpMainPage,
                navigateToHelpGraphicsPage,
                navigateToHelpAboutPage,
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}
//-------------------------
@Composable
private fun HelpMenu(
    navigateToHelpMainPage: () -> Unit,
    navigateToHelpGraphicsPage: () -> Unit,
    navigateToHelpAboutPage: () -> Unit,
) {
    HelpTopAppBarDropdownMenu(
        iconContent = {
            Icon(
                Icons.Default.MoreVert,
                stringResource(id = R.string.help_menu)
            )
        }
    ) { closeMenu ->
        DropdownMenuItem(
            leadingIcon = {Icon(imageVector = Icons.AutoMirrored.Filled.Help, contentDescription = null)}
            ,
            onClick = {
                navigateToHelpMainPage()
                //            WebViewMainScreen("file:///android_asset/help.html");
                closeMenu()
            },
            text = { Text(text = stringResource(id = R.string.help_mainPage)) }
        )
        DropdownMenuItem(
            leadingIcon = {Icon(imageVector = Icons.Filled.AppRegistration, contentDescription = null)}
            ,
            onClick = {
                navigateToHelpGraphicsPage()
                closeMenu()
            },
            text = { Text(text = stringResource(id = R.string.help_Graphics)) }
        )
        DropdownMenuItem(
            leadingIcon = {Icon(imageVector = Icons.Filled.LocalPolice, contentDescription = null)}
            ,
            onClick = {
                navigateToHelpAboutPage()
                closeMenu()
            },
            text = { Text(text = stringResource(id = R.string.help_About)) }
        )
    }
}
//-----------------------------
@Composable
private fun HelpTopAppBarDropdownMenu(
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

/*
@Preview
@Composable
private fun HelpScreenPreview(
    ) {
    HelpPageScreen(
        onBackClick={}
    )
}
*/