package com.childmathematics.android.shiftschedule.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "CountryId")
    val countryId: Int?,
    val shortName: String,
    val longName: String
)
