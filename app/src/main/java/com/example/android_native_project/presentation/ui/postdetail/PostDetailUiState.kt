package com.example.android_native_project.presentation.ui.postdetail

import com.example.android_native_project.domain.model.Post

/**
 * UI State for Post Detail screen
 */
data class PostDetailUiState(
    val post: Post? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
