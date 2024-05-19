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

package com.childmathematics.android.shiftschedule.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.childmathematics.android.shiftschedule.MainApplication
import com.childmathematics.android.shiftschedule.ui.about.AboutViewModel
import com.childmathematics.android.shiftschedule.ui.item.ItemDetailsViewModel
import com.childmathematics.android.shiftschedule.ui.item.ItemEditViewModel
import com.childmathematics.android.shiftschedule.ui.item.ItemEntryViewModel
import com.childmathematics.android.shiftschedule.ui.main.MainPageViewModel
import com.childmathematics.android.shiftschedule.ui.schedules.schedule01.Schedule01PageViewModel
import com.childmathematics.android.shiftschedule.ui.schedules.schedule500.Schedule500PageViewModel


/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 * Предоставляет Factory для создания экземпляра ViewModel для всего приложения .
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            ItemEditViewModel(
                this.createSavedStateHandle(),
                mainApplication().container.itemsRepository
            )
        }
        // Initializer for ItemEntryViewModel
        initializer {
            ItemEntryViewModel(mainApplication().container.itemsRepository)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle(),
                mainApplication().container.itemsRepository
            )
        }

        initializer {
            AboutViewModel(mainApplication().container.itemsRepository)
        }
        initializer {
            MainPageViewModel(mainApplication().container.itemsRepository)
        }
        initializer {
            Schedule500PageViewModel(mainApplication().container.itemsRepository)
        }
        /*
        initializer {
            Schedule01PageViewModel(mainApplication().container.itemsRepository)
        }

         */

    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 * Функция расширения запрашивает объект [Application] и возвращает экземпляр
 *   * [MainApplication].
 */
fun CreationExtras.mainApplication(): MainApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)
