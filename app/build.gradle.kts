import com.mercandalli.build_src.main.Const

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.mercandalli.android.apps.compose_ratio"
    compileSdk = Const.compileSdkVersion

    defaultConfig {
        applicationId = "com.mercandalli.android.apps.compose_ratio"
        minSdk = Const.minSdkVersion
        targetSdk = Const.targetSdkVersion
        versionCode = Const.featureComposeRatioVersionCode
        versionName = Const.featureComposeRatioVersionName
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    sourceSets {
        getByName("main") {
            res.setSrcDirs(
                mutableListOf(
                    "src/main/res/application_icon",
                    "src/main/res/theme"
                )
            )
        }
    }

    // https://developer.android.com/studio/write/java8-support
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
}

dependencies {
    implementation("com.github.Mercandj:compose-aspect-ratio-reference:1.00.00")

    // AndroidX
    implementation("androidx.annotation:annotation:1.6.0")
    implementation("androidx.appcompat:appcompat:1.6.1")

    // JetPack Compose
    implementation("androidx.compose.ui:ui:1.4.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation("androidx.compose.material:material-icons-extended:1.4.0")
    implementation("androidx.compose.foundation:foundation:1.4.0")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.compose.material3:material3:1.0.1")
}
