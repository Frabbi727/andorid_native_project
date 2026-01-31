package com.example.android_native_project

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for the MVVM template
 * @HiltAndroidApp triggers Hilt's code generation including a base class for the application
 */
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize app-level components here
    }
}
