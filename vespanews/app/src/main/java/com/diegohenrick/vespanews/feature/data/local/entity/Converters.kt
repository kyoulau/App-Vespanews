package com.diegohenrick.vespanews.feature.data.local.entity

import androidx.room.TypeConverters
import java.util.Date

class Converters {
    @TypeConverters
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverters
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }
}