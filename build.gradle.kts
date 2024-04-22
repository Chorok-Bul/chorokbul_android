buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.hilt.gradle.plugin)
        classpath(libs.kotlinx.serialization.plugin)
        classpath(libs.ksp.plugin)
    }
}

plugins {
    alias(libs.plugins.gradle.wrapper.upgrade) apply false
    alias(libs.plugins.kover.plugin) apply false
}