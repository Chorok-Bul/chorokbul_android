plugins {
    id("chorokbul.feature")
}

android {
    namespace = "com.chorokbul.map"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(projects.resource)
    implementation(projects.core.database)
    implementation(libs.naver.map.compose)
    implementation(libs.google.play.services.location)
    implementation(libs.naver.map.compose.location)
}