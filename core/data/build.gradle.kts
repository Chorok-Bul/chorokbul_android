plugins {
    id("chorokbul.module")
}

android {
    namespace = "com.chorokbul.data"
}

dependencies {
    implementation(projects.core.model)

    implementation(projects.core.datastore)
    implementation(projects.core.database)
}
