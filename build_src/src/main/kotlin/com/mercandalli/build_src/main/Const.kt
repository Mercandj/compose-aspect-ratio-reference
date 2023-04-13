package com.mercandalli.build_src.main

import org.gradle.api.Plugin
import org.gradle.api.Project

class Const : Plugin<Project> {

    override fun apply(project: Project) {
        // Possibly common dependencies or can stay empty
    }

    companion object {

        const val featureComposeRatioVersionCode = 1_00_04
        const val featureComposeRatioVersionName = "1.00.04"

        const val compileSdkVersion = 33
        const val targetSdkVersion = 33
        const val minSdkVersion = 21
    }
}
