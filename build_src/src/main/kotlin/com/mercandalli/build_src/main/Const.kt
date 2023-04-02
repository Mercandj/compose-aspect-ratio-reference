package com.mercandalli.build_src.main

import org.gradle.api.Plugin
import org.gradle.api.Project

class Const : Plugin<Project> {

    override fun apply(project: Project) {
        // Possibly common dependencies or can stay empty
    }

    companion object {

        const val featureVersionCode = 1_00_00
        const val featureVersionName = "1.00.00"

        const val compileSdkVersion = 33
        const val targetSdkVersion = 33
    }
}
