package com.childmathematics.android.shiftschedule.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NonWorkingDays")
data class NonWorkingDaysEntity(
    @PrimaryKey(autoGenerate = true)
    val nonWorkingDaysId : Int?,
    val languageId : Int,
    val countryId : Int,
    val year : Int,
    val month : Int,
    val day : Int,
    val name: String,
    val typ : Int?,
    val moveDateYMD : Long,
    val reason: String
)
