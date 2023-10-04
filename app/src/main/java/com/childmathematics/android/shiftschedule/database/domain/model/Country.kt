package com.childmathematics.android.shiftschedule.database.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Countries")
data class Countries(
    @PrimaryKey(autoGenerate = true)
    val countryId: Int?,
    val shortName: String,
    val longName: String
)

/*
  ContryId  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  ShortName varchar(10),
  LongName  varchar(20));

 */
