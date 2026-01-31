package com.example.android_native_project.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Network interceptor for OkHttp
 * Can be used to add headers, handle authentication, etc.
 * Currently just passes through requests
 */
class NetworkInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // You can add custom headers here
        val newRequest = originalRequest.newBuilder()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            // Add authentication headers here if needed
            // .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}
