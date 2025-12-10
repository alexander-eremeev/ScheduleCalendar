package com.childmathematics.android.shiftschedule.domain.repository

import com.childmathematics.android.shiftschedule.data.models.NonWorkingDaysEntity
import com.childmathematics.android.shiftschedule.presentation.util.UiResources
import kotlinx.coroutines.flow.Flow
import com.childmathematics.android.shiftschedule.data.nonworkingdays.NonWorkingDaysDao
import jakarta.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow


/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 * Репозиторий, который обеспечивает вставку, обновление, удаление и получение [Элемента] из
 * заданного источника данных.
  */
//=======================================================================================
interface NonWorkingDaysRepository {
    /**
     * Retrieve all the items from the the given data source.
     * Получить все элементы из данного источника данных.
     */
    fun getAllNonWorkingDaysYear(year: Int): Flow<UiResources<List<NonWorkingDaysEntity>>>
    fun getAllNonWorkingDays( year: Int, month: Int): Flow<UiResources<List<NonWorkingDaysEntity>>>
    /**
     * Retrieve an item from the given data source that matches with the [id].
     * Получите элемент из заданного источника данных, соответствующий [id].
     */
    fun getNonWorkingDay(id: Int): Flow<UiResources<List<NonWorkingDaysEntity>>>

    fun searchNonWorkingDay(query: String): Flow<UiResources<List<NonWorkingDaysEntity>>>
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
//--------------------------------------------------------------------------------------
    override fun getAllNonWorkingDaysYear(year: Int): Flow<UiResources<List<NonWorkingDaysEntity>>> =
        flow {
            emit(UiResources.Loading)
            nonWorkingDayDao.getAllNonWorkingDaysYear(year).collect { nonWorkingDays ->
                emit(UiResources.Success(nonWorkingDays))
            }
        }
            .catch { e ->
                emit(UiResources.Error(e.localizedMessage ?: "Unknown error occurred"))
            }
//--------------------------------------------------------------------------------------

    override fun getAllNonWorkingDays(year: Int, month: Int): Flow<UiResources<List<NonWorkingDaysEntity>>> =
        flow {
            emit(UiResources.Loading)
            nonWorkingDayDao.getAllNonWorkingDays(year, month).collect { nonWorkingDays ->
                emit(UiResources.Success(nonWorkingDays))
            }
        }
        .catch { e ->
            emit(UiResources.Error(e.localizedMessage ?: "Unknown error occurred"))
        }
//--------------------------------------------------------------------------------------
    override fun getNonWorkingDay(id: Int): Flow<UiResources<List<NonWorkingDaysEntity>>>
        =flow {
            emit(UiResources.Loading)
            nonWorkingDayDao.getNonWorkingDay(id).collect { nonWorkingDay ->
                emit(UiResources.Success(nonWorkingDay))
            }
        }
        .catch  { e ->
                emit(UiResources.Error(e.localizedMessage ?: "Unknown error occurred"))
        }
//-------------------------------------------------------------------------------------------------

    override fun searchNonWorkingDay(query: String): Flow<UiResources<List<NonWorkingDaysEntity>>>

        = flow {

            emit(UiResources.Loading)

            nonWorkingDayDao.search(query).collect { nonWorkingDays->
                emit(UiResources.Success(nonWorkingDays))
                }
        }
        .catch { e ->
                emit(UiResources.Error(e.localizedMessage ?: "Unknown error occurred"))
        }
//-------------------------------------------------------------------------------------------------
    override suspend fun insertNonWorkingDay(nonWorkingDaysEntity: NonWorkingDaysEntity) {

         try {
                nonWorkingDayDao.insert(nonWorkingDaysEntity)
            }
        catch (e: Exception) { //TooGenericExceptionCaught: Перехваченное исключение слишком общее.
                                // Предпочтите перехват конкретных исключений, а не текущего случая.
            throw e // Позвольте ViewModel обработать любое перехваченное исключение
            }
    }
//-------------------------------------------------------------------------------------------------
    override suspend fun deleteNonWorkingDay(nonWorkingDaysEntity: NonWorkingDaysEntity) {
        try {
            nonWorkingDayDao.delete(nonWorkingDaysEntity)
        }
        catch (e: Exception) { //TooGenericExceptionCaught: Перехваченное исключение слишком общее.
            // Предпочтите перехват конкретных исключений, а не текущего случая.
            throw e // Позвольте ViewModel обработать любое перехваченное исключение
        }
    }
//-------------------------------------------------------------------------------------------------
    override suspend fun updateNonWorkingDay(nonWorkingDaysEntity: NonWorkingDaysEntity) {
        try {
            nonWorkingDayDao.update(nonWorkingDaysEntity)
        }
        catch (e: Exception) { //TooGenericExceptionCaught: Перехваченное исключение слишком общее.
            // Предпочтите перехват конкретных исключений, а не текущего случая.
            throw e // Позвольте ViewModel обработать любое перехваченное исключение
        }
    }
}
