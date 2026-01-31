package com.example.android_native_project.domain.model

/**
 * Domain model for Post
 * This represents a post in the domain layer, independent of data sources
 */
data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)
