package com.example.android_native_project.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data Transfer Object for User from the JSONPlaceholder API
 * @JsonClass generates Moshi adapter for JSON serialization/deserialization
 */
@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "username")
    val username: String,

    @Json(name = "email")
    val email: String,

    @Json(name = "phone")
    val phone: String? = null,

    @Json(name = "website")
    val website: String? = null
)
