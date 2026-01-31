package com.example.android_native_project.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity for User table
 * Represents a user stored in the local database
 */
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String?,
    val website: String?,
    val timestamp: Long = System.currentTimeMillis() // For cache invalidation
)
