package com.childmathematics.android.shiftschedule.database.domain.model

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
/*
CREATE TABLE NonWorkingDays (
  NonWorkingDayId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  LanguageId      integer(3) NOT NULL,
  ContryId        integer(3) NOT NULL,
  Year            integer(4),
  Month           integer(2),
  Day             integer(3) NOT NULL,
  Name            varchar(50) NOT NULL,
  Typ             integer(1) NOT NULL,
  MoveDateYMD     timestamp,
  Reason          varchar(100));

 */