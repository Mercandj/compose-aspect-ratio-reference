plugins {
    // https://proandroiddev.com/stop-using-gradle-buildsrc-use-composite-builds-instead-3c38ac7a2ab3
    id("com.mercandalli.build_src.const")
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.fabric.io/public")

        // Check dependencies.
        // https://github.com/ben-manes/gradle-versions-plugin
        gradlePluginPortal()

        maven(url = "file:///users/jonathan/Documents/maven")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath(kotlin("gradle-plugin", version = "1.8.10"))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
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
