package com.example.android_native_project.data.repository

import com.example.android_native_project.domain.model.Post
import com.example.android_native_project.domain.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for Post operations
 * Defines the contract for accessing post data
 */
interface PostRepository {

    /**
     * Get all posts with cache-first strategy
     * Returns a Flow that emits Resource states (Loading, Success, Error)
     */
    fun getPosts(): Flow<Resource<List<Post>>>

    /**
     * Get a single post by ID
     * Returns a Flow that emits Resource states
     */
    fun getPostById(postId: Int): Flow<Resource<Post>>

    /**
     * Create a new post
     * Posts to the API and updates local cache
     */
    suspend fun createPost(post: Post): Resource<Post>

    /**
     * Refresh posts from the network
     * Forces a refresh from the API
     */
    suspend fun refreshPosts(): Resource<List<Post>>
}
