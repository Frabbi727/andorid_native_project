package com.example.android_native_project.presentation.ui.users

import com.example.android_native_project.domain.model.User

/**
 * UI State for User List screen
 */
data class UserListUiState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
