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

package com.childmathematics.android.shiftschedule.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 * Репозиторий, который обеспечивает вставку, обновление, удаление и получение [Элемента] из
 * заданного источника данных.
 */
interface ItemsRepository {
    /**
     * Retrieve all the items from the the given data source.
     * Получить все элементы из данного источника данных.
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     * Получите элемент из заданного источника данных, соответствующий [id].
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * Insert item in the data source
     * Вставить элемент в источник данных
     */
    suspend fun insertItem(item: Item)

    /**
     * Delete item from the data source
     * Удалить элемент из источника данных
     */
    suspend fun deleteItem(item: Item)

    /**
     * Update item in the data source
     * Обновить элемент в источнике данных
     */
    suspend fun updateItem(item: Item)
}