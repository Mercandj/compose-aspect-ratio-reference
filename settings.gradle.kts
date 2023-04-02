// https://proandroiddev.com/stop-using-gradle-buildsrc-use-composite-builds-instead-3c38ac7a2ab3
includeBuild("build_src")

rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "compose-aspect-ratio-reference"
include(
    ":app",
    ":feature_compose_ratio"
)

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
