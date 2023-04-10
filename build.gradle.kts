plugins {
    // https://proandroiddev.com/stop-using-gradle-buildsrc-use-composite-builds-instead-3c38ac7a2ab3
    id("com.mercandalli.build_src.const")
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath(kotlin("gradle-plugin", version = "1.8.20"))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")

        // JetPack Compose - Mobile
        // https://developer.android.com/jetpack/androidx/releases/compose-kotlin#pre-release_kotlin_compatibility
        // https://androidx.dev/storage/compose-compiler/repository
        // https://github.com/jimgoog/ComposeAppUsingPrereleaseComposeCompiler#project-configuration
        // https://github.com/jimgoog/ComposeAppUsingPrereleaseComposeCompiler/blob/main/settings.gradle#L14
        maven(url = "https://androidx.dev/storage/compose-compiler/repository/")
    }
}

subprojects {
    val ktlintGradleModuleExcluded = listOf(
        "feature_audio_sampler",
        "feature_mixfader",
        "feature_wallpaper_unity_aar"
    )
    if (!ktlintGradleModuleExcluded.contains(name)) {
        // KtLint - Static code analysis
        // https://github.com/pinterest/ktlint/releases
        val ktlint by configurations.creating

        dependencies {
            // KtLint - Static code analysis
            // https://github.com/pinterest/ktlint/releases
            ktlint("com.pinterest:ktlint:0.48.2")
        }

        // KtLint - Static code analysis
        // https://github.com/pinterest/ktlint/releases
        tasks.register<JavaExec>("ktlint") {
            group = "verification"
            description = "Check Kotlin code style."
            classpath = ktlint
            mainClass.set("com.pinterest.ktlint.Main")
            args("--android", "src/**/*.kt")
        }

        // KtLint - Static code format
        // https://github.com/pinterest/ktlint/releases
        tasks.register<JavaExec>("ktformat") {
            group = "verification"
            description = "Check Kotlin code style."
            classpath = ktlint
            mainClass.set("com.pinterest.ktlint.Main")
            args("--android", "-F", "src/**/*.kt")
        }
    }
}
