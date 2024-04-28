plugins {
    id("chorokbul.module")
}

android {
    namespace = "com.chorokbul.model"
}

dependencies {
    implementation(libs.bundles.room)
}
