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
}