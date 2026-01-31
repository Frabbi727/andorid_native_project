package com.example.android_native_project.data.repository

import com.example.android_native_project.data.local.database.dao.PostDao
import com.example.android_native_project.data.mapper.toDomain
import com.example.android_native_project.data.mapper.toDto
import com.example.android_native_project.data.mapper.toEntity
import com.example.android_native_project.data.remote.api.JsonPlaceholderApi
import com.example.android_native_project.domain.model.Post
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
 * Implementation of PostRepository with cache-first strategy
 *
 * Cache-first strategy:
 * 1. Emit cached data immediately (if available)
 * 2. Fetch from network
 * 3. Update cache with fresh data
 * 4. Emit updated data
 *
 * This provides offline-first capability and better UX
 */
@Singleton
class PostRepositoryImpl @Inject constructor(
    private val api: JsonPlaceholderApi,
    private val dao: PostDao
) : PostRepository {

    override fun getPosts(): Flow<Resource<List<Post>>> = flow {
        // 1. Emit loading state
        emit(Resource.Loading())

        // 2. Get cached data and emit if available
        val cachedPosts = dao.getAllPosts()
            .firstOrNull()
            ?.map { it.toDomain() }

        if (!cachedPosts.isNullOrEmpty()) {
            emit(Resource.Success(cachedPosts))
        }

        // 3. Fetch from network
        try {
            val remotePosts = api.getPosts()
            val entities = remotePosts.map { it.toEntity() }

            // 4. Update cache
            dao.deleteAllPosts()
            dao.insertPosts(entities)

            // 5. Emit fresh data from cache (single source of truth)
            val freshPosts = dao.getAllPosts()
                .firstOrNull()
                ?.map { it.toDomain() } ?: emptyList()

            emit(Resource.Success(freshPosts))

        } catch (e: Exception) {
            // If network fails but we have cache, keep showing cached data
            if (cachedPosts != null) {
                emit(
                    Resource.Success(
                        data = cachedPosts,
                        message = "Showing cached data. ${e.localizedMessage}"
                    )
                )
            } else {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getPostById(postId: Int): Flow<Resource<Post>> = flow {
        // 1. Emit loading state
        emit(Resource.Loading())

        // 2. Get cached post
        val cachedPost = dao.getPostById(postId)
            .firstOrNull()
            ?.toDomain()

        if (cachedPost != null) {
            emit(Resource.Success(cachedPost))
        }

        // 3. Fetch from network
        try {
            val remotePost = api.getPostById(postId)
            val entity = remotePost.toEntity()

            // 4. Update cache
            dao.insertPost(entity)

            // 5. Emit fresh data
            val freshPost = dao.getPostById(postId)
                .firstOrNull()
                ?.toDomain()

            if (freshPost != null) {
                emit(Resource.Success(freshPost))
            } else {
                emit(Resource.Error("Post not found"))
            }

        } catch (e: Exception) {
            // If network fails but we have cache, keep showing cached data
            if (cachedPost != null) {
                emit(
                    Resource.Success(
                        data = cachedPost,
                        message = "Showing cached data. ${e.localizedMessage}"
                    )
                )
            } else {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun createPost(post: Post): Resource<Post> {
        return withContext(Dispatchers.IO) {
            try {
                // Post to API
                val createdPost = api.createPost(post.toDto())

                // Save to cache
                dao.insertPost(createdPost.toEntity())

                Resource.Success(createdPost.toDomain())
            } catch (e: Exception) {
                Resource.Error(e.localizedMessage ?: "Failed to create post")
            }
        }
    }

    override suspend fun refreshPosts(): Resource<List<Post>> {
        return withContext(Dispatchers.IO) {
            try {
                // Fetch from network
                val remotePosts = api.getPosts()
                val entities = remotePosts.map { it.toEntity() }

                // Update cache
                dao.deleteAllPosts()
                dao.insertPosts(entities)

                // Return fresh data
                Resource.Success(entities.map { it.toDomain() })
            } catch (e: Exception) {
                Resource.Error(e.localizedMessage ?: "Failed to refresh posts")
            }
        }
    }
}
