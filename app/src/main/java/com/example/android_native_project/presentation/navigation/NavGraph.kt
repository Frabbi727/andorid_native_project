package com.example.android_native_project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.android_native_project.presentation.ui.home.HomeScreen
import com.example.android_native_project.presentation.ui.postdetail.PostDetailScreen
import com.example.android_native_project.presentation.ui.posts.PostListScreen
import com.example.android_native_project.presentation.ui.settings.SettingsScreen
import com.example.android_native_project.presentation.ui.users.UserListScreen

/**
 * Main navigation graph for the application
 * Sets up all navigation routes and their corresponding composables
 *
 * @param navController The NavHostController for navigation
 * @param startDestination The initial screen to display
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Home Screen
        composable(route = Screen.Home.route) {
            HomeScreen(
                onNavigateToPosts = {
                    navController.navigate(Screen.PostList.route)
                },
                onNavigateToUsers = {
                    navController.navigate(Screen.UserList.route)
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        // Post List Screen
        composable(route = Screen.PostList.route) {
            PostListScreen(
                onNavigateBack = { navController.popBackStack() },
                onPostClick = { postId ->
                    navController.navigate(Screen.PostDetail.createRoute(postId))
                }
            )
        }

        // Post Detail Screen with argument
        composable(
            route = Screen.PostDetail.route,
            arguments = listOf(
                navArgument("postId") {
                    type = NavType.IntType
                }
            )
        ) {
            PostDetailScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // User List Screen
        composable(route = Screen.UserList.route) {
            UserListScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Settings Screen
        composable(route = Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
