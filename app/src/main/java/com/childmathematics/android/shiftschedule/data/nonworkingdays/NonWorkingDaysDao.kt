package com.childmathematics.android.shiftschedule.data.nonworkingdays

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.childmathematics.android.shiftschedule.data.models.CountryEntity
import com.childmathematics.android.shiftschedule.data.models.NonWorkingDaysEntity
import kotlinx.coroutines.flow.Flow


/**
 * Database access object to access the Inventory database
 * Объект доступа к базе данных для доступа к базе данных инвентаризации
 */
@Dao
interface NonWorkingDaysDao {
    @Query("SELECT * from NonWorkingDays WHERE CountryId  = 7  AND Typ = 1 AND Year = :year  " +
            "ORDER BY Month ASC,Day ASC")
    fun getAllHoliDaysYear(year: Int): Flow<List<NonWorkingDaysEntity>>

    @Query("SELECT * from NonWorkingDays WHERE CountryId  = 7  AND (Typ = 2 OR Typ = 3) AND Year = :year  " +
            "ORDER BY Month ASC,Day ASC")
    fun getAllNonWorkingDaysYear(year: Int): Flow<List<NonWorkingDaysEntity>>

    @Query("SELECT * from NonWorkingDays WHERE CountryId  = 7 AND Typ = 1 AND Year = :year AND Month = :month " +
            "ORDER BY Day ASC")
    fun getAllHoliDays(year: Int, month: Int): Flow<List<NonWorkingDaysEntity>>

    @Query("SELECT Day from NonWorkingDays WHERE CountryId  = 7 AND Typ = 1 AND Year = :year AND Month = :month " +
            "ORDER BY Day ASC")
    fun getHoliDaysOnlyDays(year: Int, month: Int): Flow<List<Int>>

    @Query("SELECT * from NonWorkingDays WHERE CountryId  = 7 AND (Typ = 2 OR Typ = 3) AND Year = :year AND Month = :month " +
            "ORDER BY Day ASC")
    fun getAllNonWorkingDays(year: Int, month: Int): Flow<List<NonWorkingDaysEntity>>

    @Query("SELECT Day from NonWorkingDays WHERE CountryId  = 7 AND (Typ = 2 OR Typ = 3) AND Year = :year AND Month = :month " +
            "ORDER BY Day ASC")
    fun getNonWorkingDaysOnlyDays(year: Int, month: Int): Flow<List<Int>>

    @Query("SELECT MoveDateDay from NonWorkingDays WHERE CountryId  = 7 AND Typ = 3 " +
            "AND  MoveDateYear = :year AND  MoveDateMonth = :month " + "ORDER BY MoveDateDay ASC")
    fun getNonWorkingDaysOnlyWorkDays(year: Int, month: Int): Flow<List<Int>>


    @Query("SELECT * from NonWorkingDays WHERE NonWorkingDayId = :id")
    fun getNonWorkingDay(id: Int): Flow<List<NonWorkingDaysEntity>>

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

    @Query ( "SELECT * FROM NonWorkingDays WHERE Month LIKE :searchQuery  ")
    fun search ( searchQuery : String): Flow<List<NonWorkingDaysEntity>>

}
