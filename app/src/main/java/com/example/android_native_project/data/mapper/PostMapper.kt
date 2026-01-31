package com.example.android_native_project.data.mapper

import com.example.android_native_project.data.local.database.entities.PostEntity
import com.example.android_native_project.data.remote.dto.PostDto
import com.example.android_native_project.domain.model.Post

/**
 * Extension functions for mapping between Post data models
 * DTO (network) ↔ Entity (database) ↔ Domain (business logic)
 */

/**
 * Convert PostDto (from API) to Domain model
 */
fun PostDto.toDomain(): Post {
    return Post(
        id = this.id,
        userId = this.userId,
        title = this.title,
        body = this.body
    )
}

/**
 * Convert PostDto (from API) to PostEntity (for database)
 */
fun PostDto.toEntity(): PostEntity {
    return PostEntity(
        id = this.id,
        userId = this.userId,
        title = this.title,
        body = this.body,
        timestamp = System.currentTimeMillis()
    )
}

/**
 * Convert PostEntity (from database) to Domain model
 */
fun PostEntity.toDomain(): Post {
    return Post(
        id = this.id,
        userId = this.userId,
        title = this.title,
        body = this.body
    )
}

/**
 * Convert Domain model to PostEntity (for database)
 */
fun Post.toEntity(): PostEntity {
    return PostEntity(
        id = this.id,
        userId = this.userId,
        title = this.title,
        body = this.body,
        timestamp = System.currentTimeMillis()
    )
}

/**
 * Convert Domain model to PostDto (for API requests)
 */
fun Post.toDto(): PostDto {
    return PostDto(
        id = this.id,
        userId = this.userId,
        title = this.title,
        body = this.body
    )
}
