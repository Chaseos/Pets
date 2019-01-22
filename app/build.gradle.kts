import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.chaseolson.pets"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "petFinderKey", "2922d4a277ba3eca9ed8342c8aee7070")
        }
        getByName("debug") {
            buildConfigField("String", "petFinderKey", "\"2922d4a277ba3eca9ed8342c8aee7070\"")
        }
    }
}

dependencies {
    val kotlinVersion = "1.3.11"
    val appCompatVersion = "1.0.2"
    val recyclerViewVersion = "1.0.0"
    val constraintLayoutVersion = "2.0.0-alpha3"
    val coroutinesVersion = "1.1.0"
    val archLifecycleVersion = "1.1.1"
    val navigationVersion = "1.0.0-alpha09"
    val retrofitVersion = "2.5.0"
    val picassoVersion = "2.71828"
    val tickarooVersion = "0.8.13"
    val androidxVersion = "1.0.0"
    val junitVersion = "4.12"
    val mockitoVersion = "1.10.19"

    implementation(kotlin("stdlib-jdk7", kotlinVersion))
    implementation("androidx.appcompat", "appcompat", appCompatVersion)
    implementation("androidx.recyclerview", "recyclerview", recyclerViewVersion)
    implementation("androidx.constraintlayout", "constraintlayout", constraintLayoutVersion)

    //Coroutines
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", coroutinesVersion)

    //ViewModel & LiveData
    implementation("android.arch.lifecycle", "extensions", archLifecycleVersion)

    //Navigation
    implementation("android.arch.navigation", "navigation-fragment-ktx", navigationVersion)
    implementation("android.arch.navigation", "navigation-ui-ktx", navigationVersion)

    //Retrofit
    implementation("com.squareup.retrofit2", "retrofit", retrofitVersion)

    //Picasso
    implementation("com.squareup.picasso", "picasso", picassoVersion)

    //Tickaroo Xml Parser
    implementation("com.tickaroo.tikxml", "core", tickarooVersion)
    implementation("com.tickaroo.tikxml", "annotation", tickarooVersion)
    implementation("com.tickaroo.tikxml", "retrofit-converter", tickarooVersion)
    kapt("com.tickaroo.tikxml", "processor", tickarooVersion)

    //Testing
    testImplementation("junit", "junit", junitVersion)
    testImplementation("androidx.test", "core", androidxVersion)
    testImplementation("org.mockito", "mockito-core", mockitoVersion)
}
