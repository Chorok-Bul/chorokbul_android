plugins {
    id("chorokbul.module")
}

android {
    namespace = "com.chorokbul.model"
}

dependencies {
    implementation(libs.bundles.room)
    implementation(libs.naver.map.tedclustering)
    implementation(libs.naver.map.compose)
}
