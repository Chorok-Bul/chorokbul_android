plugins {
    id("chorokbul.module")
}

android {
    // Compose
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = catalog.version("composeCompiler")
    }

    lint {
        disable += "MissingTranslation"
        disable += "ComposeViewModelInjection"
        abortOnError = false
    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(libs.bundles.compose)
}