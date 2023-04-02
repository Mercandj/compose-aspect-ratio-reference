package com.mercandalli.build_src.main

import org.gradle.api.Plugin
import org.gradle.api.Project

class Const : Plugin<Project> {

    override fun apply(project: Project) {
        // Possibly common dependencies or can stay empty
    }

    companion object {

        const val featureComposeRatioVersionName = "0.00.01"

        const val compileSdkVersion = 33
    }
}