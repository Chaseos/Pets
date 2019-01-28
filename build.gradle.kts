// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val gradleVersion = "3.4.0-beta02"
    val kotlinVersion = "1.3.20"

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath ("com.android.tools.build", "gradle", gradleVersion)
        classpath (kotlin("gradle-plugin", kotlinVersion))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class.java) {
    delete (rootProject.buildDir)
}
