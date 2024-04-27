plugins {
    id("chorokbul.module")
}

android {
    namespace = "com.chorokbul.database"
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.bundles.room)
}
