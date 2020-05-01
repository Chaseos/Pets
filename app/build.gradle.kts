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
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.chaseolson.pets"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "petFinderKey", Petfinder_CONSUMER_KEY)
            buildConfigField("String", "petFinderSecret", Petfinder_CONSUMER_SECRET)
        }
        getByName("debug") {
            buildConfigField("String", "petFinderKey", Petfinder_CONSUMER_KEY)
            buildConfigField("String", "petFinderSecret", Petfinder_CONSUMER_SECRET)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions.apply {
        allWarningsAsErrors = true
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures.dataBinding = true
}

dependencies {
    implementation(Libs.kotlin_stdlib_jdk7)
    implementation(Libs.appcompat)
    implementation(Libs.recyclerview)
    implementation(Libs.constraintlayout)
    implementation(Libs.play_services_location)
    kapt(Libs.com_android_databinding_compiler)

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
    implementation(Libs.paging_runtime_ktx)

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

    // Testing
    testImplementation(Libs.junit)
    testImplementation(Libs.androidx_test_core)
    testImplementation(Libs.mockito_core)

    // Glide
    implementation(Libs.glide)
    annotationProcessor(Libs.com_github_bumptech_glide_compiler)

    // Epoxy
    implementation(Libs.epoxy)
    implementation(Libs.epoxy_paging)
    implementation(Libs.epoxy_databinding)
    kapt (Libs.epoxy_processor)

    // Koin
    implementation(Libs.koin_android)
    implementation(Libs.koin_android_viewmodel)
    implementation(Libs.koin_android_scope)

    // Timber
    implementation(Libs.timber)
}
