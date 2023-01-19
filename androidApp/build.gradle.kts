plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "ru.lyrian.kotlinmultiplatformsandbox.android"
    compileSdk = AppConstants.compileSdk

    defaultConfig {
        applicationId = "ru.lyrian.kotlinmultiplatformsandbox.android"
        minSdk = AppConstants.minSdk
        targetSdk = AppConstants.targetSdk
        versionCode = AppConstants.versionCode
        versionName = AppConstants.versionName
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
    implementation(projects.shared)
    implementation(libs.bundles.compose)
    implementation(libs.coroutines.android)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit)
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.coroutines.test)
}
