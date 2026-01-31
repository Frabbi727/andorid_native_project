package com.example.android_native_project.presentation.ui.posts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android_native_project.R
import com.example.android_native_project.presentation.ui.components.AppTopBar
import com.example.android_native_project.presentation.ui.components.EmptyView
import com.example.android_native_project.presentation.ui.components.ErrorView
import com.example.android_native_project.presentation.ui.components.LoadingIndicator
import com.example.android_native_project.presentation.ui.posts.components.PostItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * Post List screen - displays all posts with pull-to-refresh
 */
@Composable
fun PostListScreen(
    onNavigateBack: () -> Unit,
    onPostClick: (Int) -> Unit,
    viewModel: PostListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.posts_title),
                canNavigateBack = true,
                onNavigateBack = onNavigateBack
            )
        }
    ) { paddingValues ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = uiState.isRefreshing),
            onRefresh = { viewModel.refresh() },
            modifier = Modifier.padding(paddingValues)
        ) {
            when {
                uiState.isLoading && uiState.posts.isEmpty() -> {
                    LoadingIndicator()
                }
                uiState.error != null && uiState.posts.isEmpty() -> {
                    ErrorView(
                        message = uiState.error ?: stringResource(R.string.error_generic),
                        onRetry = { viewModel.refresh() }
                    )
                }
                uiState.posts.isEmpty() -> {
                    EmptyView(
                        message = stringResource(R.string.posts_empty),
                        description = stringResource(R.string.posts_empty_description),
                        actionText = stringResource(R.string.refresh),
                        onAction = { viewModel.refresh() }
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            items = uiState.posts,
                            key = { it.id }
                        ) { post ->
                            PostItem(
                                post = post,
                                onClick = { onPostClick(post.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}
