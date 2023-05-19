package com.childmathematics.android.shiftschedule.database.data.network

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import com.childmathematics.android.shiftschedule.database.domain.model.Countries
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDAO {

    @Query("SELECT * FROM Countries ORDER BY CountryId ASC")
    fun getCountries(): Flow<List<Countries>>

    @Query("SELECT * FROM Countries WHERE CountryId = :countryId")
    fun getCountry(countryId: Int): Flow<Countries>

    @Insert(onConflict = IGNORE)
    fun addCountry(shortName: Countries)

    @Update
    fun updateCountry(shortName: Countries)

    @Delete
    fun deleteCountry(shortName: Countries)


}
