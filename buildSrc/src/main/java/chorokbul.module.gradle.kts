plugins {
    id("com.google.devtools.ksp")
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.kapt")
}

kotlin {
    sourceSets.all {
        kotlin.srcDir("build/generated/ksp/$name/kotlin")
    }
}

android {
    // Kotlin
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    // Android
    compileSdk = catalog.version("compileSdk").toInt()
    defaultConfig {
        minSdk = catalog.version("minSdk").toInt()
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(libs.bundles.kotlin)
    implementation(libs.timber)

    implementation(libs.bundles.hilt)
    kapt(catalog.library("hilt-compiler"))
}