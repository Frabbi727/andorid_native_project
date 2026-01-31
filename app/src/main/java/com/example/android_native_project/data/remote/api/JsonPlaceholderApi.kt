package com.example.android_native_project.data.remote.api

import com.example.android_native_project.data.remote.dto.PostDto
import com.example.android_native_project.data.remote.dto.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Retrofit API interface for JSONPlaceholder API
 * Defines all API endpoints for the application
 */
interface JsonPlaceholderApi {

    /**
     * Get all posts
     * @return List of posts from the API
     */
    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    /**
     * Get a single post by ID
     * @param id The ID of the post to retrieve
     * @return The post with the specified ID
     */
    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): PostDto

    /**
     * Create a new post
     * @param post The post to create
     * @return The created post (with ID assigned by the server)
     */
    @POST("posts")
    suspend fun createPost(@Body post: PostDto): PostDto

    /**
     * Get all users
     * @return List of users from the API
     */
    @GET("users")
    suspend fun getUsers(): List<UserDto>

    /**
     * Get a single user by ID
     * @param id The ID of the user to retrieve
     * @return The user with the specified ID
     */
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserDto
}
