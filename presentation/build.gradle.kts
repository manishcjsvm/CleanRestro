plugins {
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.hilt)
}

android {
    namespace = "com.assignment.presentation"
    compileSdk = Integer.parseInt(libs.versions.compileSDK.get())

    defaultConfig {
        minSdk = Integer.parseInt(libs.versions.minSDK.get())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
}

dependencies {


    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.material)


    // Compose libraries
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)


    // Tests
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.turbine)

    // Debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Coroutines
    implementation(libs.kotlin.coroutines)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)


    // Navigation
    implementation(libs.androidx.navigation)

    // Hilt ViewModel
    implementation(libs.hilt.navigation.compose)

    // Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)

    implementation(project(":domain"))
    implementation(project(":common"))


}