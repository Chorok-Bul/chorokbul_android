plugins {
    id("chorokbul.feature")
}

android {
    namespace = "com.chorokbul.splash"
}

dependencies {
    implementation(projects.resource)
    implementation(projects.core.domain)
}
