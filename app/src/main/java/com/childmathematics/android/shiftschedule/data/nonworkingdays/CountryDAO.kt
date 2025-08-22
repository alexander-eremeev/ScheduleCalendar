package com.childmathematics.android.shiftschedule.data.nonworkingdays

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.childmathematics.android.shiftschedule.data.models.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDAO {

    @Query("SELECT * FROM Countries ORDER BY CountryId ASC")
    fun getCountries(): Flow<List<Country>>

    @Query("SELECT * FROM Countries WHERE CountryId = :countryId")
    fun getCountry(countryId: Int): Flow<Country>

    @Insert(onConflict = IGNORE)
    suspend fun addCountry(shortName: Country)

    @Update
    suspend fun updateCountry(shortName: Country)

    @Delete
    suspend fun deleteCountry(shortName: Country)


}
