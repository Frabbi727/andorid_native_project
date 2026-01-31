package com.example.android_native_project.presentation.navigation

/**
 * Sealed class representing all navigation destinations in the app
 * Provides type-safe navigation with compile-time route checking
 */
sealed class Screen(val route: String) {
    /**
     * Home screen - landing page with navigation options
     */
    data object Home : Screen("home")

    /**
     * Post list screen - displays all posts
     */
    data object PostList : Screen("post_list")

    /**
     * Post detail screen - displays a single post
     * @param postId The ID of the post to display
     */
    data object PostDetail : Screen("post_detail/{postId}") {
        fun createRoute(postId: Int) = "post_detail/$postId"
    }

    /**
     * User list screen - displays all users
     */
    data object UserList : Screen("user_list")

    /**
     * Settings screen - app settings and language selection
     */
    data object Settings : Screen("settings")
}
