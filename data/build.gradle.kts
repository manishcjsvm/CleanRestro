plugins {
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.hilt)
}

android {
    namespace = "com.assignment.data"
    compileSdk = Integer.parseInt(libs.versions.compileSDK.get())

    defaultConfig {
        minSdk = Integer.parseInt(libs.versions.minSDK.get())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BASE_URL", "\"https://api.disneyapi.dev/\"")

        vectorDrawables {
            useSupportLibrary = true
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
    buildFeatures.buildConfig = true
}

dependencies {


    // Tests
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Retrofit
    implementation(libs.retofit)
    implementation(libs.gson.converter)
    implementation(libs.logging.interceptor)

    // Gson
    implementation(libs.gson)

    // Coroutines
    implementation(libs.kotlin.coroutines)

    implementation(project(":domain"))
    implementation(project(":common"))
}