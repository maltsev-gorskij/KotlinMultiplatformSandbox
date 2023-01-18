plugins {
    id("com.android.library")
}

android {
    namespace = "ru.lyrian.kotlinmultiplatformsandbox"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res", "src/commonMain/resources")
        }
    }
}
