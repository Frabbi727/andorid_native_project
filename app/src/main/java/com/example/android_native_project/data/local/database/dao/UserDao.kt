package com.example.android_native_project.data.local.database.dao

import androidx.room.*
import com.example.android_native_project.data.local.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for User operations
 * Provides methods to interact with the users table
 */
@Dao
interface UserDao {

    /**
     * Get all users from the database
     * Returns a Flow that emits the list whenever the data changes
     */
    @Query("SELECT * FROM users ORDER BY id ASC")
    fun getAllUsers(): Flow<List<UserEntity>>

    /**
     * Get a single user by ID
     */
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: Int): Flow<UserEntity?>

    /**
     * Insert a list of users
     * OnConflictStrategy.REPLACE will update existing users
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    /**
     * Insert a single user
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    /**
     * Update a user
     */
    @Update
    suspend fun updateUser(user: UserEntity)

    /**
     * Delete a user
     */
    @Delete
    suspend fun deleteUser(user: UserEntity)

    /**
     * Delete all users
     * Useful for refreshing the cache
     */
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}
