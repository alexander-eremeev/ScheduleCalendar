package com.childmathematics.android.shiftschedule.data.repository

import com.childmathematics.android.shiftschedule.data.models.NonWorkingDaysEntity
import com.childmathematics.android.shiftschedule.data.models.UiResources
import com.childmathematics.android.shiftschedule.data.nonworkingdays.NonWorkingDaysDao
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class NonWorkingDaysRepositoryImpl @Inject constructor(
    private val nonWorkingDayDao: NonWorkingDaysDao) : NonWorkingDaysRepository {
    override fun getAllNonWorkingDaysStream(): Flow<UiResources<List<NonWorkingDaysEntity>>> = flow {
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