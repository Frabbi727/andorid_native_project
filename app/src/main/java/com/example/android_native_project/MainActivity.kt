package com.example.android_native_project

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.android_native_project.data.local.datastore.PreferencesManager
import com.example.android_native_project.presentation.navigation.NavGraph
import com.example.android_native_project.ui.theme.Android_native_projectTheme
import com.example.android_native_project.util.LocaleHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main Activity - Entry point of the application
 * Handles navigation and locale management
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Apply saved locale
        lifecycleScope.launch {
            val savedLanguage = preferencesManager.languageFlow.firstOrNull()
            if (savedLanguage != null) {
                LocaleHelper.setLocale(this@MainActivity, savedLanguage)
            }
        }

        enableEdgeToEdge()

        setContent {
            Android_native_projectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        // This will be called before onCreate, so we can't use injected dependencies here
        // The locale will be set in onCreate when we have access to preferences
        super.attachBaseContext(newBase)
    }
}

/**
 * Main navigation composable
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavGraph(navController = navController)
}