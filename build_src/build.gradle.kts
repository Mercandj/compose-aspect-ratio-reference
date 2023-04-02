plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("constPlugin") {
            id = "com.mercandalli.build_src.const"
            implementationClass = "com.mercandalli.build_src.main.Const"
        }
    }
}
