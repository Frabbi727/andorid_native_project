package com.example.android_native_project.data.repository

import com.example.android_native_project.domain.model.User
import com.example.android_native_project.domain.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for User operations
 * Defines the contract for accessing user data
 */
interface UserRepository {

    /**
     * Get all users with cache-first strategy
     * Returns a Flow that emits Resource states (Loading, Success, Error)
     */
    fun getUsers(): Flow<Resource<List<User>>>

    /**
     * Get a single user by ID
     * Returns a Flow that emits Resource states
     */
    fun getUserById(userId: Int): Flow<Resource<User>>

    /**
     * Refresh users from the network
     * Forces a refresh from the API
     */
    suspend fun refreshUsers(): Resource<List<User>>
}
