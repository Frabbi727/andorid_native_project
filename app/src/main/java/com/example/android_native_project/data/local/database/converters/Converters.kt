package com.example.android_native_project.data.local.database.converters

import androidx.room.TypeConverter
import java.util.Date

/**
 * Type converters for Room database
 * Used to convert complex types to/from primitive types for storage
 */
class Converters {

    /**
     * Convert timestamp (Long) to Date
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    /**
     * Convert Date to timestamp (Long)
     */
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    // Add more type converters here as needed
    // For example, if you need to store a List<String> in a column:
    //
    // @TypeConverter
    // fun fromStringList(value: String): List<String> {
    //     val listType = object : TypeToken<List<String>>() {}.type
    //     return Gson().fromJson(value, listType)
    // }
    //
    // @TypeConverter
    // fun toStringList(list: List<String>): String {
    //     return Gson().toJson(list)
    // }
}
