# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Keep line numbers for better crash reports
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Keep generic signature (needed for reflection)
-keepattributes Signature

# Keep annotation attributes
-keepattributes *Annotation*

# Keep Exceptions
-keepattributes Exceptions

# ===========================
# Retrofit & OkHttp
# ===========================
-dontwarn retrofit2.**
-dontwarn okhttp3.**
-dontwarn okio.**
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keep class okio.** { *; }

# Keep Retrofit interfaces
-keep interface com.example.android_native_project.data.remote.api.** { *; }

# Keep HTTP methods annotations
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# ===========================
# Moshi
# ===========================
-dontwarn com.squareup.moshi.**
-keep class com.squareup.moshi.** { *; }
-keepclassmembers class ** {
    @com.squareup.moshi.* <methods>;
}

# Keep all data classes for Moshi
-keep @com.squareup.moshi.JsonClass class * { *; }
-keep class com.example.android_native_project.data.remote.dto.** { *; }
-keep class com.example.android_native_project.domain.model.** { *; }

# Keep generated Moshi adapters
-keep class **JsonAdapter { *; }

# ===========================
# Room
# ===========================
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Keep Room entities and DAOs
-keep class com.example.android_native_project.data.local.database.entities.** { *; }
-keep interface com.example.android_native_project.data.local.database.dao.** { *; }

# ===========================
# Hilt
# ===========================
-dontwarn com.google.dagger.**
-keep class dagger.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }

# Keep Hilt modules
-keep class com.example.android_native_project.di.** { *; }

# ===========================
# Kotlin Coroutines
# ===========================
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# ===========================
# DataStore
# ===========================
-keep class androidx.datastore.*.** { *; }

# ===========================
# Compose
# ===========================
-keep class androidx.compose.** { *; }
-keep class androidx.compose.runtime.** { *; }

# ===========================
# App-specific classes
# ===========================
# Keep data models
-keep class com.example.android_native_project.domain.model.** { *; }

# Keep ViewModels
-keep class com.example.android_native_project.presentation.ui.**.ViewModel { *; }

# Keep repositories
-keep class com.example.android_native_project.data.repository.** { *; }

# ===========================
# General Android
# ===========================
-keep class android.** { *; }
-keep class androidx.** { *; }

# Keep Parcelable classes
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}