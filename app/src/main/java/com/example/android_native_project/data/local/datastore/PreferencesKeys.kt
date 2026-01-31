package com.example.android_native_project.data.local.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Keys for DataStore preferences
 * Define all preference keys here for type safety
 */
object PreferencesKeys {
    /**
     * Key for storing the selected language code (e.g., "en", "bn")
     */
    val LANGUAGE_KEY = stringPreferencesKey("language")

    // Add more preference keys here as needed
    // val THEME_KEY = stringPreferencesKey("theme")
    // val USER_ID_KEY = intPreferencesKey("user_id")
}
