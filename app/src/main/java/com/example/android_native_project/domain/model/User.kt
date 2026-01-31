package com.example.android_native_project.domain.model

/**
 * Domain model for User
 * This represents a user in the domain layer, independent of data sources
 */
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String? = null,
    val website: String? = null
)
