package com.example.android_native_project.di

import com.example.android_native_project.data.repository.PostRepository
import com.example.android_native_project.data.repository.PostRepositoryImpl
import com.example.android_native_project.data.repository.UserRepository
import com.example.android_native_project.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing repository implementations
 * Uses @Binds to bind interfaces to their implementations
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Binds PostRepositoryImpl to PostRepository interface
     */
    @Binds
    @Singleton
    abstract fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository

    /**
     * Binds UserRepositoryImpl to UserRepository interface
     */
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}
