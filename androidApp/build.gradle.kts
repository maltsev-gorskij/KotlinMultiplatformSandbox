plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "ru.lyrian.kotlinmultiplatformsandbox.android"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "ru.lyrian.kotlinmultiplatformsandbox.android"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

dependencies {
    // Importing shared module with typesafe gradle accessor
    implementation(projects.shared)

    // All compose ui related dependencies bundle
    implementation(libs.bundles.compose)

    // Coroutines
    implementation(libs.coroutines.android)

    // Koin DI for Android and Compose
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    // Unit testing
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit)
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.coroutines.test)
}
