plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    // add
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.longlegsdev.note"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.longlegsdev.note"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    /* add dependency */
    // hilt
    // https://github.com/google/dagger
    val hilt_version = "2.51.1"
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")

    // hilt navigation compose
    // https://developer.android.com/jetpack/androidx/releases/hilt?hl=ko
    val hilt_navigation_version = "1.2.0"
    implementation("androidx.hilt:hilt-navigation-compose:$hilt_navigation_version")

    // room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    // coroutine
    val coroutine_version = "1.8.1"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version")

    // lifecycle-extensions
    val lifecycle_version = "2.8.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version") // ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version") // LiveData
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version") // Lifecycles only (without ViewModel or LiveData)

    // https://developer.android.com/develop/ui/compose/navigation?hl=ko
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // compose base
    // https://developer.android.com/jetpack/androidx/releases/compose-foundation?hl=ko
    val foundation_version = "1.7.0-beta01"
    implementation("androidx.compose.foundation:foundation:$foundation_version")

    // timber
    // git : https://github.com/JakeWharton/timber
    val timber_version = "5.0.1"
    implementation("com.jakewharton.timber:timber:$timber_version")

    // lottie
    // https://github.com/airbnb/lottie/blob/master/android-compose.md
    val lottie_version = "6.4.1"
    implementation ("com.airbnb.android:lottie-compose:$lottie_version")
}