package com.childmathematics.android.shiftschedule.data.repository

import com.childmathematics.android.shiftschedule.data.Item
import com.childmathematics.android.shiftschedule.data.models.NonWorkingDaysEntity
import com.childmathematics.android.shiftschedule.data.models.UiResources
import kotlinx.coroutines.flow.Flow


/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 * Репозиторий, который обеспечивает вставку, обновление, удаление и получение [Элемента] из
 * заданного источника данных.
 * interface UserRepository {
 * fun getUsers(): Flow<UIResources<List<UserEntity>>>
 * suspend fun addUser(user: UserEntity)
 * suspend fun deleteUser(user: UserEntity)
 * suspend fun clearUsers()
 * fun searchUsers(query: String): Flow<UIResources<List<UserEntity>>>
 * }
 */
interface NonWorkingDaysRepository {
    /**
     * Retrieve all the items from the the given data source.
     * Получить все элементы из данного источника данных.
     */
    fun getAllNonWorkingDaysStream(): Flow<UiResources<List<NonWorkingDaysEntity>>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     * Получите элемент из заданного источника данных, соответствующий [id].
     */
    fun getNonWorkingDayStream(id: Int): Flow<UiResources<NonWorkingDaysEntity?>>

    fun searchNonWorkingDayStream(query: String): Flow<UiResources<List<NonWorkingDaysEntity>>>

    /**
     * Insert item in the data source
     * Вставить элемент в источник данных
     */
    suspend fun insertNonWorkingDay(nonWorkingDaysEntity: NonWorkingDaysEntity)

    /**
     * Delete item from the data source
     * Удалить элемент из источника данных
     */
    suspend fun deleteNonWorkingDay(nonWorkingDaysEntity: NonWorkingDaysEntity)

    /**
     * Update item in the data source
     * Обновить элемент в источнике данных
     */
    suspend fun updateNonWorkingDay(nonWorkingDaysEntity: NonWorkingDaysEntity)

    //==================================
//    val destinations: List<ExploreModel> = destinationsLocalDataSource.craneDestinations
    //val svmselection: List<LocalDate>
}
