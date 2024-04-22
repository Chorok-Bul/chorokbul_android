plugins {
    id("chorokbul.module")
}

android {
    namespace = "com.chorokbul.database"
}

dependencies {
    implementation(libs.bundles.room)
}