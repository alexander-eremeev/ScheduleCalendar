package com.childmathematics.android.shiftschedule.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NonWorkingDays")
data class NonWorkingDaysEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "NonWorkingDayId")
    val nonWorkingDayId : Int,   //?
    @ColumnInfo(name = "LanguageId")
    val languageId : Int,      //?
    @ColumnInfo(name = "CountryId")
    val countryId : Int,       //?
    @ColumnInfo(name = "Year")
    val year : Int?,
    @ColumnInfo(name = "Month")
    val month : Int?,
    @ColumnInfo(name = "Day")
    val day : Int,         //?
    @ColumnInfo(name = "Name")
    val name: String,      //?
    @ColumnInfo(name = "Typ")
    val typ : Int,     //?
    @ColumnInfo(name = "MoveDateYear")
    val moveDateYear : Int?,
    @ColumnInfo(name = "MoveDateMonth")
    val moveDateMonth : Int?,
    @ColumnInfo(name = "MoveDateDay")
    val moveDateDay : Int?,
    @ColumnInfo(name = "Reason")
    val reason: String?
)
