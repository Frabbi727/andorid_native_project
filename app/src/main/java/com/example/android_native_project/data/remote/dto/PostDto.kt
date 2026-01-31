package com.example.android_native_project.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data Transfer Object for Post from the JSONPlaceholder API
 * @JsonClass generates Moshi adapter for JSON serialization/deserialization
 */
@JsonClass(generateAdapter = true)
data class PostDto(
    @Json(name = "id")
    val id: Int,

    @Json(name = "userId")
    val userId: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "body")
    val body: String
)
