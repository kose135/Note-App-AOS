// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false

    // Hilt
    // https://developer.android.com/training/dependency-injection/hilt-android?hl=ko
    id("com.google.dagger.hilt.android") version "2.51" apply false
}