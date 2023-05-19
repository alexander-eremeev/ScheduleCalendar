package com.childmathematics.android.shiftschedule.basis.datasource.local

import androidx.room.TypeConverter
import com.childmathematics.android.basement.core.datetime.toLocalDateTime
import com.childmathematics.android.basement.core.datetime.toMillis
import java.time.LocalDateTime

class DateConverter {

    @TypeConverter
    fun toDate(date: Long?): LocalDateTime? {
        if (date == null) return null

        return date.toLocalDateTime()
    }

    @TypeConverter
    fun toDateLong(date: LocalDateTime?): Long? {
        if (date == null) return null

        return date.toMillis()
    }

}
