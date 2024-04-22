plugins {
    id("chorokbul.module")
}

android {
    namespace = "com.chorokbul.network"
}

dependencies {
    implementation(libs.bundles.retrofit)
}