package com.example.android_native_project.data.mapper

import com.example.android_native_project.data.local.database.entities.UserEntity
import com.example.android_native_project.data.remote.dto.UserDto
import com.example.android_native_project.domain.model.User

/**
 * Extension functions for mapping between User data models
 * DTO (network) ↔ Entity (database) ↔ Domain (business logic)
 */

/**
 * Convert UserDto (from API) to Domain model
 */
fun UserDto.toDomain(): User {
    return User(
        id = this.id,
        name = this.name,
        username = this.username,
        email = this.email,
        phone = this.phone,
        website = this.website
    )
}

/**
 * Convert UserDto (from API) to UserEntity (for database)
 */
fun UserDto.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.name,
        username = this.username,
        email = this.email,
        phone = this.phone,
        website = this.website,
        timestamp = System.currentTimeMillis()
    )
}

/**
 * Convert UserEntity (from database) to Domain model
 */
fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        name = this.name,
        username = this.username,
        email = this.email,
        phone = this.phone,
        website = this.website
    )
}

/**
 * Convert Domain model to UserEntity (for database)
 */
fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.name,
        username = this.username,
        email = this.email,
        phone = this.phone,
        website = this.website,
        timestamp = System.currentTimeMillis()
    )
}
