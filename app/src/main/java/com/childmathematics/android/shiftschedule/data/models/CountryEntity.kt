package com.childmathematics.android.shiftschedule.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ContryId")
    val countryId: Int?,
    @ColumnInfo(name = "ShortName")
   val shortName: String,
    @ColumnInfo(name = "LongName")
    val longName: String
)
