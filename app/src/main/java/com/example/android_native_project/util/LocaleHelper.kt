package com.example.android_native_project.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.Locale

/**
 * Helper class for managing app locale/language
 * Provides functions to change language at runtime
 */
object LocaleHelper {

    /**
     * Set the app locale
     * @param context Application or Activity context
     * @param languageCode Language code (e.g., "en", "bn")
     * @return Context with updated locale
     */
    fun setLocale(context: Context, languageCode: String): Context {
        return updateResources(context, languageCode)
    }

    /**
     * Get the current locale language code
     */
    fun getLanguage(context: Context): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales[0].language
        } else {
            @Suppress("DEPRECATION")
            context.resources.configuration.locale.language
        }
    }

    /**
     * Update resources with new locale
     */
    private fun updateResources(context: Context, languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.createConfigurationContext(configuration)
        } else {
            @Suppress("DEPRECATION")
            context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
            context
        }
    }

    /**
     * Get locale display name
     * @param languageCode Language code
     * @return Display name of the language
     */
    fun getLanguageDisplayName(languageCode: String): String {
        val locale = Locale(languageCode)
        return locale.getDisplayLanguage(locale).replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(locale) else it.toString()
        }
    }

    /**
     * Check if the language code is supported
     */
    fun isSupportedLanguage(languageCode: String): Boolean {
        return languageCode in listOf(
            Constants.LANGUAGE_ENGLISH,
            Constants.LANGUAGE_BANGLA
        )
    }
}
