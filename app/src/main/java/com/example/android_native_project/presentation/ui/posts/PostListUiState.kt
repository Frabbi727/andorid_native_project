package com.example.android_native_project.presentation.ui.posts

import com.example.android_native_project.domain.model.Post

/**
 * UI State for Post List screen
 */
data class PostListUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)
