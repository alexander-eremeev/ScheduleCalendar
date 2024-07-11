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

package com.childmathematics.android.shiftschedule

import android.app.Application



import com.childmathematics.android.shiftschedule.data.AppContainer
import com.childmathematics.android.shiftschedule.data.AppDataContainer

//public class MainApplication extends Application {

class MainApplication : Application() {
//public class InventoryApplication  extends Application {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     * Экземпляр AppContainer, используемый остальными классами для получения зависимостей.
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
