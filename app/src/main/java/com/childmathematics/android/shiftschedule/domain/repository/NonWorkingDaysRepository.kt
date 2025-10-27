package com.childmathematics.android.shiftschedule.domain.repository

import com.childmathematics.android.shiftschedule.data.Item
import com.childmathematics.android.shiftschedule.data.models.NonWorkingDaysEntity
import com.childmathematics.android.shiftschedule.data.models.UiResources
import kotlinx.coroutines.flow.Flow
import com.childmathematics.android.shiftschedule.data.nonworkingdays.NonWorkingDaysDao
import jakarta.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow


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
//=======================================================================================
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
    /*
        val destinations: List<ExploreModel> = destinationsLocalDataSource.craneDestinations
        val svmselection: List<LocalDate>
     */
}
//=======================================================================================
class NonWorkingDaysRepositoryImpl @Inject constructor(
    private val nonWorkingDayDao: NonWorkingDaysDao) : NonWorkingDaysRepository {
    override fun getAllNonWorkingDaysStream(): Flow<UiResources<List<NonWorkingDaysEntity>>> =
        flow {
            emit(UiResources.Loading)
            nonWorkingDayDao.getAllNonWorkingDays().collect { nonWorkingDays ->
                emit(UiResources.Success(nonWorkingDays))
            }
        }.catch { e ->
            emit(UiResources.Error(e.localizedMessage ?: "Unknown error occurred"))
        }

    override fun getNonWorkingDayStream(id: Int): Flow<UiResources<NonWorkingDaysEntity?>> {
        TODO("Not yet implemented")
    }

    override fun searchNonWorkingDayStream(query: String): Flow<UiResources<List<NonWorkingDaysEntity>>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertNonWorkingDay(nonWorkingDaysEntity: NonWorkingDaysEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNonWorkingDay(nonWorkingDaysEntity: NonWorkingDaysEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateNonWorkingDay(nonWorkingDaysEntity: NonWorkingDaysEntity) {
        TODO("Not yet implemented")
    }
    /*
    override suspend fun addNonWorkingDay(nonWorkingDay: NonWorkingDaysEntity) {
        try {
            nonWorkingDayDao.insertNonWorkingDay(nonWorkingDay)
        } catch (e: Exception) {
            throw e // Let the ViewModel handle any caught exception
        }
    }
    override suspend fun deleteNonWorkingDay(nonWorkingDay: NonWorkingDaysEntity) {
        try {
            nonWorkingDayDao.delete(nonWorkingDay)
        } catch (e: Exception) {
            throw e
        }
    }

     */
    /*
    override suspend fun clearNonWorkingDays() {
        try {
            nonWorkingDayDao.clearNonWorkingDays()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun searchNonWorkingDays(query: String): Flow<UIResources<List<NonWorkingDaysEntity>>> = flow {
        emit(UIResources.Loading)
        nonWorkingDayDao.searchNonWorkingDays(query).collect { nonWorkingDays ->
            emit(UIResources.Success(nonWorkingDays))
        }
    }.catch { e ->
        emit(UIResources.Error(e.localizedMessage ?: "Unknown error occurred"))
    }

     */
}
