// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Libs.kotlin_gradle_plugin)
        classpath(Libs.kotlin_serialization)
        classpath(Libs.com_android_tools_build_gradle)
    }
}

//TODO Figuring out how to update this to latest
//./gradlew buildSrcVersions
plugins {
    `kotlin-dsl`
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}
