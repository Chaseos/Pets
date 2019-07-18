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

    dataBinding.isEnabled = true
}

dependencies {
    implementation(Libs.kotlin_stdlib_jdk7)
    implementation(Libs.appcompat)
    implementation(Libs.recyclerview)
    implementation(Libs.constraintlayout)
    implementation(Libs.play_services_location)

    //Coroutines
    implementation(Libs.kotlinx_coroutines_core)
    implementation(Libs.kotlinx_coroutines_android)

    //ViewModel & LiveData
    implementation(Libs.android_arch_lifecycle_extensions)
    implementation(Libs.lifecycle_viewmodel_savedstate)

    //Navigation
    implementation(Libs.navigation_fragment)
    implementation(Libs.navigation_ui)

    //Material
    implementation(Libs.material)

    //Paging
    implementation(Libs.paging_runtime)

    //KTX
    implementation(Libs.core_ktx)
    implementation(Libs.fragment_ktx)
    implementation(Libs.palette_ktx)
    implementation(Libs.collection_ktx)
    implementation(Libs.navigation_fragment_ktx)
    implementation(Libs.navigation_ui_ktx)
    implementation(Libs.lifecycle_viewmodel_ktx)
    implementation(Libs.lifecycle_livedata_ktx)

    //Retrofit
    implementation(Libs.retrofit)

    //Picasso
    implementation(Libs.picasso)

    //Tickaroo Xml Parser
    implementation(Libs.com_tickaroo_tikxml_core)
    implementation(Libs.annotation)
    implementation(Libs.retrofit_converter)
    kapt(Libs.processor)

    //Testing
    testImplementation(Libs.junit)
    testImplementation(Libs.androidx_test_core)
    testImplementation(Libs.mockito_core)
}
