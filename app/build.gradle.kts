plugins {
    id("com.android.application")
    id("kotlinx-serialization")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

val Petfinder_CONSUMER_KEY: String by project
val Petfinder_CONSUMER_SECRET: String by project

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.chaseolson.pets"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "petFinderKey", Petfinder_CONSUMER_KEY)
        buildConfigField("String", "petFinderSecret", Petfinder_CONSUMER_SECRET)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {}
    }

    dataBinding.isEnabled = true
}

dependencies {
    implementation(Libs.kotlin_stdlib_jdk7)
    implementation(Libs.appcompat)
    implementation(Libs.recyclerview)
    implementation(Libs.constraintlayout)
    implementation(Libs.play_services_location)

    // Coroutines
    implementation(Libs.kotlinx_coroutines_core)
    implementation(Libs.kotlinx_coroutines_android)

    // ViewModel & LiveData
    implementation(Libs.android_arch_lifecycle_extensions)
    implementation(Libs.lifecycle_viewmodel_savedstate)

    //Navigation
    implementation(Libs.navigation_fragment)
    implementation(Libs.navigation_ui)

    // Material
    implementation(Libs.material)
    implementation(Libs.swiperefreshlayout)

    // Paging
    implementation(Libs.paging_runtime)

    // KTX
    implementation(Libs.core_ktx)
    implementation(Libs.fragment_ktx)
    implementation(Libs.palette_ktx)
    implementation(Libs.collection_ktx)
    implementation(Libs.navigation_fragment_ktx)
    implementation(Libs.navigation_ui_ktx)
    implementation(Libs.lifecycle_viewmodel_ktx)
    implementation(Libs.lifecycle_livedata_ktx)
    implementation(Libs.kotlinx_serialization_runtime)

    // Retrofit
    implementation(Libs.retrofit)
    implementation(Libs.retrofit2_kotlinx_serialization_converter)
    implementation(Libs.retrofit2_kotlin_coroutines_adapter)

    // Picasso
    implementation(Libs.picasso)

    // Tickaroo Xml Parser
    implementation(Libs.com_tickaroo_tikxml_core)
    implementation(Libs.annotation)
    implementation(Libs.retrofit_converter)
    kapt(Libs.processor)

    // Testing
    testImplementation(Libs.junit)
    testImplementation(Libs.androidx_test_core)
    testImplementation(Libs.mockito_core)

    // Glide
    implementation(Libs.glide)
    annotationProcessor(Libs.com_github_bumptech_glide_compiler)

    // Epoxy
    implementation(Libs.epoxy)

    // Koin
    implementation(Libs.koin_android)
    implementation(Libs.koin_android_viewmodel)
    implementation(Libs.koin_android_scope)
}
