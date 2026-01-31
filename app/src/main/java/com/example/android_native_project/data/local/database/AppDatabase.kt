package com.example.android_native_project.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_native_project.data.local.database.converters.Converters
import com.example.android_native_project.data.local.database.dao.PostDao
import com.example.android_native_project.data.local.database.dao.UserDao
import com.example.android_native_project.data.local.database.entities.PostEntity
import com.example.android_native_project.data.local.database.entities.UserEntity

/**
 * Room Database for the application
 * Contains all entities and provides DAOs
 */
@Database(
    entities = [
        PostEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides access to Post operations
     */
    abstract fun postDao(): PostDao

    /**
     * Provides access to User operations
     */
    abstract fun userDao(): UserDao
}
