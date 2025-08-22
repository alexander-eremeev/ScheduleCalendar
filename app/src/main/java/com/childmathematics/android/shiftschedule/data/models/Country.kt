package com.childmathematics.android.shiftschedule.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Countries")
data class Country(
    @PrimaryKey(autoGenerate = true)
    val countryId: Int?,
    val shortName: String,
    val longName: String
)
