package com.example.android_native_project.domain.util

/**
 * A sealed class that represents the state of a resource (API call, database operation, etc.)
 * This is used to wrap data with loading, success, and error states
 *
 * Usage:
 * ```
 * when (resource) {
 *     is Resource.Loading -> showLoading()
 *     is Resource.Success -> showData(resource.data)
 *     is Resource.Error -> showError(resource.message)
 * }
 * ```
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    /**
     * Represents a loading state
     * Can optionally include cached data to show while loading
     */
    class Loading<T>(data: T? = null) : Resource<T>(data)

    /**
     * Represents a successful state with data
     * Can optionally include a message (e.g., "Data loaded from cache")
     */
    class Success<T>(data: T, message: String? = null) : Resource<T>(data, message)

    /**
     * Represents an error state
     * Can optionally include cached/fallback data to show despite the error
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
