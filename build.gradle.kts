// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Libs.kotlin_gradle_plugin)
        classpath(Libs.kotlin_serialization)
        classpath("com.android.tools.build:gradle:4.1.0-alpha08")
    }
}

//TODO Figuring out how to update this to latest
//./gradlew buildSrcVersions
plugins {
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
