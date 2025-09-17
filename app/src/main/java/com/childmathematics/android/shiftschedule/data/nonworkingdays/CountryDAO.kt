package com.childmathematics.android.shiftschedule.data.nonworkingdays

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.childmathematics.android.shiftschedule.data.models.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDAO {

    @Query("SELECT * FROM Countries ORDER BY CountryId ASC")
    fun getCountries(): Flow<List<CountryEntity>>

    @Query("SELECT * FROM Countries WHERE CountryId = :countryId")
    fun getCountry(countryId: Int): Flow<CountryEntity>

    @Insert(onConflict = IGNORE)
    suspend fun addCountry(shortName: CountryEntity)

    @Update
    suspend fun updateCountry(shortName: CountryEntity)

    @Delete
    suspend fun deleteCountry(shortName: CountryEntity)


}
