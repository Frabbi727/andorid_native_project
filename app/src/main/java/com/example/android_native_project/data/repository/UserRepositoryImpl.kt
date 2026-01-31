package com.example.android_native_project.data.repository

import com.example.android_native_project.data.local.database.dao.UserDao
import com.example.android_native_project.data.mapper.toDomain
import com.example.android_native_project.data.mapper.toEntity
import com.example.android_native_project.data.remote.api.JsonPlaceholderApi
import com.example.android_native_project.domain.model.User
import com.example.android_native_project.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of UserRepository with cache-first strategy
 */
@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: JsonPlaceholderApi,
    private val dao: UserDao
) : UserRepository {

    override fun getUsers(): Flow<Resource<List<User>>> = flow {
        // 1. Emit loading state
        emit(Resource.Loading())

        // 2. Get cached data and emit if available
        val cachedUsers = dao.getAllUsers()
            .firstOrNull()
            ?.map { it.toDomain() }

        if (!cachedUsers.isNullOrEmpty()) {
            emit(Resource.Success(cachedUsers))
        }

        // 3. Fetch from network
        try {
            val remoteUsers = api.getUsers()
            val entities = remoteUsers.map { it.toEntity() }

            // 4. Update cache
            dao.deleteAllUsers()
            dao.insertUsers(entities)

            // 5. Emit fresh data from cache
            val freshUsers = dao.getAllUsers()
                .firstOrNull()
                ?.map { it.toDomain() } ?: emptyList()

            emit(Resource.Success(freshUsers))

        } catch (e: Exception) {
            // If network fails but we have cache, keep showing cached data
            if (cachedUsers != null) {
                emit(
                    Resource.Success(
                        data = cachedUsers,
                        message = "Showing cached data. ${e.localizedMessage}"
                    )
                )
            } else {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getUserById(userId: Int): Flow<Resource<User>> = flow {
        // 1. Emit loading state
        emit(Resource.Loading())

        // 2. Get cached user
        val cachedUser = dao.getUserById(userId)
            .firstOrNull()
            ?.toDomain()

        if (cachedUser != null) {
            emit(Resource.Success(cachedUser))
        }

        // 3. Fetch from network
        try {
            val remoteUser = api.getUserById(userId)
            val entity = remoteUser.toEntity()

            // 4. Update cache
            dao.insertUser(entity)

            // 5. Emit fresh data
            val freshUser = dao.getUserById(userId)
                .firstOrNull()
                ?.toDomain()

            if (freshUser != null) {
                emit(Resource.Success(freshUser))
            } else {
                emit(Resource.Error("User not found"))
            }

        } catch (e: Exception) {
            // If network fails but we have cache, keep showing cached data
            if (cachedUser != null) {
                emit(
                    Resource.Success(
                        data = cachedUser,
                        message = "Showing cached data. ${e.localizedMessage}"
                    )
                )
            } else {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun refreshUsers(): Resource<List<User>> {
        return withContext(Dispatchers.IO) {
            try {
                // Fetch from network
                val remoteUsers = api.getUsers()
                val entities = remoteUsers.map { it.toEntity() }

                // Update cache
                dao.deleteAllUsers()
                dao.insertUsers(entities)

                // Return fresh data
                Resource.Success(entities.map { it.toDomain() })
            } catch (e: Exception) {
                Resource.Error(e.localizedMessage ?: "Failed to refresh users")
            }
        }
    }
}
