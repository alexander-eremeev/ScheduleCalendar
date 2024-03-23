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

package com.childmathematics.android.shiftschedule.ui.homepage

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.childmathematics.android.shiftschedule.presentation.HomePageShow
import com.childmathematics.android.shiftschedule.ui.AppViewModelProvider

/**
 * Entry route for Home screen
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePageScreen(
    openDrawer: () -> Unit,
//    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomePageViewModel = viewModel(factory = AppViewModelProvider.Factory),
//    viewModel: StatisticsViewModel = hiltViewModel(),
//    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
           /*
           Собирает значения из этого StateFlow и представляет его последнее значение через State. StateFlow.value
           используется в качестве начального значения. Каждый раз, когда в StateFlow будет отправляться новое значение,
           возвращаемое состояние будет обновляться, вызывая рекомпозицию каждого использования State.value.
            */
    val homePageUiState by viewModel.homePageUiState.collectAsState()
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
   val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState, { true },)

    val scrollState = rememberScrollState()
    Scaffold(
//        scaffoldState = scaffoldState,
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)

//            .alignBy.verticalScroll()
//            .verticalScroll(scrollState)
                ,
        topBar = { HomePageTopAppBar(openDrawer) }
    ) {
//        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        /*
        Box(modifier = modifier
            .padding(top = 20.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
        ){
            HomePageShow()
        }

         */
        HomePageShow()
    }
}
