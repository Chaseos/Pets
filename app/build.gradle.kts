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
            buildConfigField("String", "petFinderKey", "\"2922d4a277ba3eca9ed8342c8aee7070\"")
        }
        getByName("debug") {
            buildConfigField("String", "petFinderKey", "\"2922d4a277ba3eca9ed8342c8aee7070\"")
        }
    }
}

dependencies {
    implementation(Libs.kotlin_stdlib_jdk7)
    implementation(Libs.appcompat)
    implementation(Libs.recyclerview)
    implementation(Libs.constraintlayout)

    //Coroutines
    implementation(Libs.kotlinx_coroutines_core)
    implementation(Libs.kotlinx_coroutines_android)

    //ViewModel & LiveData
    implementation(Libs.android_arch_lifecycle_extensions)

    //Navigation
    implementation(Libs.navigation_fragment)
    implementation(Libs.navigation_ui)

    //Material
    implementation(Libs.material)

    //Paging
    implementation(Libs.paging_runtime)

    //Retrofit
    implementation(Libs.retrofit)

    //Picasso
    implementation(Libs.picasso)

    //Tickaroo Xml Parser
    implementation(Libs.tickaroo_tikxml_core)
    implementation(Libs.tickaroo_annotation)
    implementation(Libs.tickaroo_retrofit_converter)
    kapt(Libs.tickaroo_processor)

    //Testing
    testImplementation(Libs.junit)
    testImplementation(Libs.androidx_test_core)
    testImplementation(Libs.mockito_core)

    implementation("com.google.android.gms:play-services-location:16.0.0")
}
