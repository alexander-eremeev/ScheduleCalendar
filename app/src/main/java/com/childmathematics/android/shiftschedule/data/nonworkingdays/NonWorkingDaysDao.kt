package com.childmathematics.android.shiftschedule.data.nonworkingdays

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.childmathematics.android.shiftschedule.data.models.NonWorkingDaysEntity
import kotlinx.coroutines.flow.Flow


/**
 * Database access object to access the Inventory database
 * Объект доступа к базе данных для доступа к базе данных инвентаризации
 */
@Dao
interface NonWorkingDaysDao {

    @Query("SELECT * from NonWorkingDays ORDER BY name ASC")
    fun getAllNonWorkingDays(): Flow<List<NonWorkingDaysEntity>>

    @Query("SELECT * from NonWorkingDays WHERE nonWorkingDaysId = :id")
    fun getNonWorkingDay(id: Int): Flow<NonWorkingDaysEntity>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    // Укажите стратегию конфликта как ИГНОРИРОВАТЬ, когда пользователь пытается добавить
    // существующий элемент в базе данных. Комната игнорирует конфликт.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nonWorkingDaysEntity: NonWorkingDaysEntity)

    @Update
    suspend fun update(nonWorkingDaysEntity: NonWorkingDaysEntity)

    @Delete
    suspend fun delete(nonWorkingDaysEntity: NonWorkingDaysEntity)
}
