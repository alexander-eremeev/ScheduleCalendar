/*
 * Copyright (C) 2023 The Android Open Source Project
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

package com.childmathematics.android.shiftschedule.ui.schedule500

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.presentation.Schedule500Sample
import com.childmathematics.android.shiftschedule.ui.AppViewModelProvider
import com.childmathematics.android.shiftschedule.ui.navigation.NavigationDestination
import com.childmathematics.android.shiftschedule.ui.navigation.components.PagesTopAppBar
import kotlinx.coroutines.ExperimentalCoroutinesApi


object Schedule500Destination : NavigationDestination {
    override val route = "Schedule500"
    override val titleRes = R.string.app_name
}

/**
 * Entry route for Home screen
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Schedule500Screen(
    navigateToItemEntry: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: Schedule500ViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
           /*
           Собирает значения из этого StateFlow и представляет его последнее значение через State. StateFlow.value
           используется в качестве начального значения. Каждый раз, когда в StateFlow будет отправляться новое значение,
           возвращаемое состояние будет обновляться, вызывая рекомпозицию каждого использования State.value.
            */
    val schedule500UiState by viewModel.schedule500UiState.collectAsState()
        /*
        Возвращает TopAppBarScrollBehavior. Верхняя панель приложения, настроенная с помощью этого
        TopAppBarScrollBehavior, немедленно свернется при извлечении содержимого и сразу же появится при перемещении
        содержимого вниз.
        Параметры:
            состояние — объект состояния, который будет использоваться для управления или наблюдения за
                состоянием прокрутки верхней панели приложения. См. RememberTopAppBarState, чтобы узнать о состоянии,
                которое запоминается в композициях.
            canScroll — обратный вызов, используемый для определения того, должны ли события прокрутки обрабатываться
                этим EnterAlwaysScrollBehavior.
            snapAnimationSpec — необязательный AnimationSpec, который определяет, как верхняя панель приложения
                привязывается к полностью свернутому или полностью развернутому состоянию, когда перетаскивание или
                перетаскивание прокручивают ее в промежуточное положение.
            flingAnimationSpec — необязательный DecayAnimationSpec, определяющий, как отображать верхнюю
                панель приложения, когда пользователь перемещает саму панель приложения или содержимое под ней.
         */
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PagesTopAppBar(
                title = stringResource(Schedule500Destination.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.item_entry_title)
                )
            }
        },
    ) { innerPadding ->
        /*
        Schedule500Body(
            itemList = schedule500UiState.itemList,
            onItemClick = navigateToItemUpdate,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
        */
        Schedule500Sample(true)
    }
}
