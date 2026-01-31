package com.example.android_native_project.data.local.database.dao

import androidx.room.*
import com.example.android_native_project.data.local.database.entities.PostEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Post operations
 * Provides methods to interact with the posts table
 */
@Dao
interface PostDao {

    /**
     * Get all posts from the database
     * Returns a Flow that emits the list whenever the data changes
     */
    @Query("SELECT * FROM posts ORDER BY id DESC")
    fun getAllPosts(): Flow<List<PostEntity>>

    /**
     * Get a single post by ID
     */
    @Query("SELECT * FROM posts WHERE id = :postId")
    fun getPostById(postId: Int): Flow<PostEntity?>

    /**
     * Insert a list of posts
     * OnConflictStrategy.REPLACE will update existing posts
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)

    /**
     * Insert a single post
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: PostEntity)

    /**
     * Update a post
     */
    @Update
    suspend fun updatePost(post: PostEntity)

    /**
     * Delete a post
     */
    @Delete
    suspend fun deletePost(post: PostEntity)

    /**
     * Delete all posts
     * Useful for refreshing the cache
     */
    @Query("DELETE FROM posts")
    suspend fun deleteAllPosts()
}
