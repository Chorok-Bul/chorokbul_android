plugins {
    id("chorokbul.module")
}

android {
    namespace = "com.chorokbul.domain"
}

dependencies {
    implementation(projects.core.data)
}
