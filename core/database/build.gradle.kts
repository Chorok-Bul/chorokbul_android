plugins {
    id("chorokbul.module")
}

android {
    namespace = "com.chorokbul.database"
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
}
