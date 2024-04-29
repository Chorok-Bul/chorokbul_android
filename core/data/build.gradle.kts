plugins {
    id("chorokbul.module")
}

android {
    namespace = "com.chorokbul.data"
}

dependencies {
    implementation(projects.core.datastore)
    implementation(projects.core.database)
}
