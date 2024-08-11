plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.musashi.weatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.musashi.weatherapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}



kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //Splash Api
    implementation(libs.androidx.core.splashscreen)

    //hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.appcompat)
    kapt(libs.dagger.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)


    //arrow
    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //serialization
    implementation(libs.kotlinx.serialization.json)

    //room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //data preferences
    implementation(libs.androidx.datastore.preferences)


    //material 3
    implementation(libs.material3)
    implementation(libs.androidx.material.icons.extended)

    //define a BOM and its version
    implementation(platform(libs.okhttp.bom))

    //define any required OkHttp artifacts without version
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //accompanist
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.permissions)

    //notification
    implementation(libs.androidx.core)

    //work manager
    implementation(libs.androidx.work.runtime)

    //change calendar
    implementation(libs.persiandate)

    //vico plotting chart
//    implementation(libs.vico.compose)
//    implementation(libs.vico.compose.m3)



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}